#!/usr/bin/env python
# vim:fileencoding=utf-8 :et:ts=4:sts=4
#
# Copyright 2015 René Jeschke <rene_jeschke@yahoo.de>
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

import os
import re
import errno
import urllib2
import sys
import subprocess

group_id = "com.github.rjeschke"
artifact = "txtmark"
jar_dest = os.path.join(os.path.expanduser("~"), ".txtmark_jar", "txtmark.jar")

oss_snapshots = "https://oss.sonatype.org/content/repositories/snapshots"
maven_repo1 = "https://repo1.maven.org/maven2"

snap_url = oss_snapshots + "/" + group_id.replace(".", "/") + "/" + artifact
rel_url = maven_repo1 + "/" + group_id.replace(".", "/") + "/" + artifact

def mkdirs(path):
    """mkdir -p"""
    try:
        os.makedirs(path)
    except OSError as e:
        if e.errno != errno.EEXIST or not os.path.isdir(path):
            raise


def read_mvn_infos(url):
    response = urllib2.urlopen(url + "/maven-metadata.xml")
    latest = None
    version = None
    last_modified = 0
    for line in response.read().split("\n"):
        line = line.strip()
        if re.match("^<latest>.+</latest>$", line):
            latest = line[8:-9]
        elif re.match("^<lastUpdated>.+</lastUpdated>$", line):
            last_modified = long(line[13:-14])
        elif not latest and re.match("^<version>.+</version>$", line):
            version = line[9:-10]

    if latest:
        return [latest, last_modified]
    return [version, last_modified]


def get_snapshot_version(url, version):
    response = urllib2.urlopen(url + "/maven-metadata.xml")
    timestamp = None
    build_number = 0
    for line in response.read().split("\n"):
        line = line.strip()
        if not timestamp and re.match("^<timestamp>.*</timestamp>$", line):
            timestamp = line[11:-12]
        elif build_number == 0 and re.match("^<buildNumber>.*</buildNumber>$", line):
            build_number = int(line[13:-14])
    return url + "/" + artifact + "-" + version[:version.find("-")] + "-" + timestamp + "-" + str(build_number) + ".jar"


def download(is_snap, version):
    u = None
    if is_snap:
        u = get_snapshot_version(snap_url + "/" + version, version)
    else:
        u = rel_url + "/" + version + "/" + artifact + "-" + version + ".jar"

    response = urllib2.urlopen(u)
    with open(jar_dest, "wb") as fd:
        fd.write(response.read())


def fetch_artifact(force_update):
    if force_update or not os.path.exists(jar_dest):
        mkdirs(os.path.dirname(jar_dest))
        rel = read_mvn_infos(rel_url)
        snp = read_mvn_infos(snap_url)

        if snp[1] > rel[1]:
            download(True, snp[0])
        else:
            download(False, rel[0])


if __name__ == "__main__":
    force_update = False
    if len(sys.argv) > 1:
        force_update = sys.argv[1] == "-u" or sys.argv[1] == "--update"

    fetch_artifact(force_update)

    cmd = ["java", "-cp", jar_dest, "com.github.rjeschke.txtmark.cmd.Run"]
    cmd.extend(sys.argv[2 if force_update else 1:])

    exit(subprocess.call(cmd))

