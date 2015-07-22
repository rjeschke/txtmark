# Introduction

Fiken.no is an online accounting system aimed at making accounting easy for small businesses.This document describes
 Fiken's API. The current version  of the API is aimed at exporting a sale made in an external system to Fiken for 
 accounting purposes. A typical scenario will be a webshop exporting sales.

We plan to support other processes in future version of this API like sending invoices from Fiken.

## Using the API

Please notify customer service before implementing this API. We will then assign you a technical contact.

Use of this API in production environments on live data is associated with a fee. Please contact Fiken for further details.

## Fiken's REST style

We use the HAL media type which is a simplistic JSON-based data format. It's two main advantages compared to using plain
JSON data is that it has a standard way of conveying links in the document and it can embedded other resources inside a
response. The links and embedded data are inside the two object properties _links and _embedded.

Links are used to point the resource client to data that can be discovered from this resource, in a similar fashion that
one links from one news story to other related news stories. One important addition that HAL's linking concept brings
in addition to normal web links are link relations or rels for short. A link relation documents what kind of link it is
and it tells the client what kind of data it can find if it follows the link. There is a set of standardized link
relations in IANA's link relation registry. Any non-URI relations comes from this link registry. RFC5988 - Web
Linking describes more on the link relation concepts. 

Embedded resources is a way to allow for fine grained resources, without the overhead of the client having to do too 
many requests. Embedding uses link relations like links, but instead of including just a reference to the data, the 
actual objects are inlined inside the document. The object keys can be either full URIs or names registered in the 
link relations registry.

An example HAL response for an imaginary "news item" resource looks like this:

    {
      "title": "Kasper arrestert igjen!",
      "body": "Kasper (36) stjal i går hatten til Politimester Sebastian, men ble arrestert etter kort tid."
      "published": "1955-05-06",
      "_links": {
        "self": {
          "href": "..."
        },
        "http://example.org/rels/related": {
          "href": "..."
        }
      },
      "_embedded": {
        "http://example.org/rels/comment": [
          {
            "text": "...",
            "author": "Tante Sofie",
            "_links": {
              "self": {
                "href": "..."
              },
              "author": {
                "href": "..."
              }
            }
          }
        ]
      }
    }

This document describes a news item with three properties; title, body and published. In addition the document has two 
links, one called self and one called http://example.org/rels/related. Fiken will use both the standardized link 
relations and our own relations. The first link is a pointer back to the resource itself, and the second link is a 
reference to related news stories. If the client is rendering the news story it can choose to follow the related link 
and display the headlines of the stores as a part of the rendering.

The document also includes a list of comments. The object key http://example.org/rels/comment is a normal link relation, but the data is inlined. Each object in the array is a full HAL object so they also include _links and could also include other _embedded resources. If a client wanted to update the comment it could do so by doing a PUT request with the just the embedded object and send it to the comment's self link.

## Base URL

All URLs start with https://fiken.no/api/v1. Note that TLS is required, unencrypted HTTP is not supported. Although we currently redirect HTTP requests to HTTPS, you are not allowed to this with your application as using HTTP is a security risk. In the future we might respond with 403 Forbidden instead.

## Request

### Authentication

The authentication is done with HTTP Basic Authentication. Your normal email and password could be used for authentication, but for audit purposes it is recommended that you create a separate user which access Fiken through the API. Even if the user who changed the object is not displayed in Fiken today we still store the data in our audit logging.

To test the authentication do a GET to https://fiken.no/api/v1/whoAmI

## Response

### Response code

All HTTP codes should be expected with their normal semantics. These are some of the common ones:

 * 200 for successful GET
 * 201 for successful POST where you get a Location-header for the created content
 * 204 for DELETE/PUT and POSTS that doesn't always create a single new resource
 * 400 when invalid content has be sent (for instance a required field is missing, unexpected fields, wrong format, etc)
 * 401: when the user is not authenticated
 * 403: when the user does not have the proper authorization 
 * 404: you know this one... 
 * 405: When you are trying a method to a resource which doesn't support it (i.e. DELETE on an account).
 * 415: Wrong media type. we accept application/json only.
 * 418: Wow, you win a free dinner with the Fiken team.

### Content

The default content type on the result of GET requests is application/hal+json. On successful POSTs/PUTs and DELETEs an empty body is returned. For successful POSTs a Location header is given in most cases. 

### Errors

On many types of errors (client and server side) the body will contain a vnd.error+json result. Please check the message in this result AND the HTTP code before contacting support for any questions.

## Audit

The client MAY set an X-Request-ID header. If set it has to be a valid UUID. If it is not present or invalid, the server will generate a new X-Request-ID. All responses will always contain this X-Request-ID which could be used for audit and support purposes. We use this header field in your logs and audit trail, and it's a good idea for you to do the same.

## Data Types

### Date

Dates are represented as strings formatted as YYYY-MM-DD.

Examples:

January 1st, 1970: "1970-1-1"

### Amount

Amounts are always represented as a number, and specifies the number of cents in the amount. Fractions will be ignored.

Examples:

One thousand: 100000

One hundred and twelwe cents: 10012

### Account

An account is a string with either four digits, or four digits, a colon and five digits ("reskontro"). Examples:

 * 3020
 * 1500:10001

## Start URL

The start URL is https://fiken.no/api/v1. This is the only URL that the your client shall access that should be hard coded in the application. The rest of the URLs should be discovered by the _links.

## Live testing

You can use the HAL Browser installed at https://fiken.no/api/v1/browser.html to browse/test the API. 

# Rels

<table class="table">
<thead>
<tr>
<th>URI</th>
<th>Methods</th>
<th>Objects</th>
<th>Typical use case</th>
</tr>
</thead>
<tbody>
<tr>
  <td>https://fiken.no/api/v1/rel/companies</td>
  <td>GET</td>
  <td><a href="#company">Company</a></td>
  <td>Get companies (name and organization number) along with links to sales, accounts and contacts</td>
</tr>
<tr>
  <td>https://fiken.no/api/v1/rel/sales</td>
  <td>GET, POST</td>
  <td><a href="#sale">Sale</a></td>
  <td>Register new sales in Fiken (and list existing ones). Get links to add attachments and payments.</td>
</tr>
<tr>
  <td>https://fiken.no/api/v1/rel/attachments</td>
  <td>GET, POST</td>
  <td>Attachment</td>
  <td>Add ("bilag") to sales, such as invoices, payment receipts, etc.</td>
</tr>
<tr>
  <td>https://fiken.no/api/v1/rel/contacts</td>
  <td>GET, POST, PUT</td>
  <td>Contact</td>
  <td>Add, update and find contacts (customers and suppliers).</td>
</tr>
<tr>
  <td>https://fiken.no/api/v1/rel/payments</td>
  <td>GET, POST</td
  ><td>Payment</td>
  <td>Add payments to a sale.</td>
</tr>
<tr>
  <td>https://fiken.no/api/v1/rel/accounts</td>
  <td>GET</td><td>Account</td>
  <td>Find the companies bank accounts in Fiken. Used for registering payments and income accounts on sales</td>
</tr>
</tbody>
</table>

# Objects

<a id="company"></a>
## Company

<table class="table">
<thead>
<tr>
  <th>Field</th>
  <th>Type</th>
  <th>Always present</th>
  <th>Description</th>
</tr>
</thead>
<tbody>
<tr>
  <td>name</td>
  <td>String</td>
  <td>Yes</td>
  <td>The company name</td>
</tr>
<tr>
  <td>organizationNumber</td>
  <td>String</td>
  <td>No</td>
  <td></td>
</tr>
</tbody>
</table>

### Example

    {
      "_links": {
        "self": {
          "href": "https://fiken.no/api/v1/companies"
        }
      },
      "_embedded": {
        "https://fiken.no/api/v1/rel/companies": [
          {
            "name": "Min første bedrift",
            "organizationNumber": "987654321",
            "_links": {
              "self": {
                "href": "https://fiken.no/api/v1/companies/min-forste-bedrift"
              }
            }
          },
          ...
        ]
      }
    }

## Sale 

Field
Type
Required
Description
date</td><td>Date</td><td>yes</td><td>The date the sale was done
kind</td><td>String</td><td>yes</td><td>
One of:
CASH_SALE
EXTERNAL_INVOICE
identifier</td><td>String (max 50)</td><td>yes</td><td>Invoice/sale number or similar.
lines</td><td>Array[OrderLine] (see below)</td><td>yes</td><td>At least one line has to be included
paymentAccount</td><td>String</td><td>maybe</td><td>For instance "1920:10001"
paymentDate</td><td>Date</td><td>maybe</td><td> 
customer</td><td>URI</td><td>maybe</td><td>
The related customer for this sale.

### Kind

Two kinds of sales can be registered; cash sales and invoices issued by other systems. A cash sale is a sale that was 
paid at the same time as the transaction was done ("Kontantsalg"); although not necessarily paid in cash.  

If an external system issued an invoice it can also be registered in Fiken. If a payment account and date is included, 
the invoice will be marked as payed. If not, payments can be registered on this invoice at a later time.

Field
Cash sale
External invoice
customer</td><td>must not be included</td><td>required
paymentAccount</td><td>required</td><td>
optional
If present, paymentDate is required
paymentDate</td><td>required, must be the same as the sale date</td><td>
optional,
If present, paymentAccount is required

## OrderLine

Field
Type
Required
Description
description</td><td>String (max 200)</td><td> </td><td>A description of the line (product name, etc)
netPrice</td><td>Amount</td><td>Yes</td><td>The net price in øre
vat</td><td>Amount</td><td>Yes</td><td>The VAT in øre 
incomeAccount</td><td>Account</td><td> </td><td>The income account. If not provided a sensible default is chosen
vatType</td><td>String</td><td>Yes </td><td>
One of: {"HIGH", "MEDIUM", "LOW", "EXEMPT", "OUTSIDE", "NONE"} .
 "HIGH" is the most common. 

## Links

rel
References
https://fiken.no/api/v1/rels/attachments</td><td>Attachments of this sale
https://fiken.no/api/v1/rels/payments</td><td>Payments of this sale

## Example

    {
      "date": "2014-04-22",
      "identifier": "1337",
      "lines": [
        {
          "description": "12 x SKU 7461 Blue high-speed Yo-Yo",
          "netPrice" : 12000,
          "vat" : 6250,
          "incomeAccount": "3000",
          "vatType" : "HIGH"
        }
      ],
      "kind": "CASH_SALE",
      "paymentAccount" : "1920:10001",
      "paymentDate": "2014-04-22"
    }
     
     
    {
      "date": "2014-12-14",
      "identifier": "1338",
      "lines": [
        {
          "description": "Something spitzy",
          "netPrice" : 1200,
          "vat" : 625,
          "vatType" : "HIGH"
        }
      ],
      "kind": "EXTERNAL_INVOICE",
      "company": "https://fiken.no/api/v1/companies/aleksander-blomskold-consulting",
      "customer": "https://fiken.no/api/v1/companies/aleksander-blomskold-consulting/contacts/1813088"
    }
   
## Attachment
 
The content to post should be a form/multipart with the following parts:

 * "SaleAttachment" of content-type application/json
 * "AttachmentFile" of one of the following content-types:
    * image/png
    * image/jpeg
    * image/gif
    * application/pdf
 
## SaleAttachment 
Field
Type
Required
Description
filename</td><td>String (max 200)</td><td>Yes </td><td>The filename. Must end with either .png, .jpeg, .jpg, .gif or .pdf
comment</td><td>String (max 4000)</td><td>No</td><td>A comment for this attachment.
attachToPayment</td><td>boolean</td><td>Yes</td><td>True if this attachment may document the payment (i.e. transaction receipt from credit card/payment company, export from bank, etc.)
attachToSale</td><td>boolean</td><td>Yes </td><td>True if this attachment may document the sale (i.e. invoice, etc)

At least one of attachToPayment and attachToSale must be true.

### Example

With curl:

    curl -n -v https://fiken.no/api/v1/companies/aleksander-blomskold-consulting/sales/1135071/attachments  \
    -F "AttachmentFile=@adwords.png" -F 'SaleAttachment={"filename":"adwords.png", "attachToSale": true, "attachToPayment": false}'

## Account

Note that the accounts URL is templated with the accounting year (four digits).

### Example

    {
      "_links": {
        "self": {
          "href": "https://fiken.no/api/v1/companies/min-forste-bedrift/accounts/{year}"
        }
      },
      "_embedded": {
        "accounts": [
          {
            "code": "1001",
            "name": "Forskning og utvikling, ervervet",
            "_links": {
              "self": {
                "href": "..."
              }
            }
          },
          {
            "code": "1500:10021",
            "name": "Kaptein Sabeltan",
            "_links": {
              "self": {
                "href": "..."
              }
            }
          }
        ]
      }
    }
    
## Contact

Field
Type
Required
Mutable
Description
name</td><td>String</td><td>yes</td><td>Yes</td><td>The (company) name of the customer
email</td><td>String</td><td>no</td><td>Yes</td><td> 
organizationIdentifier</td><td>String</td><td>no</td><td>Yes</td><td>Organization number or similar
address.postalCode</td><td>String</td><td>no</td><td>Yes</td><td> 
address.postalPlace</td><td>String</td><td>no</td><td>Yes</td><td> 
address.address1</td><td>String</td><td>no</td><td>Yes</td><td> 
address.address2</td><td>String</td><td>no</td><td>Yes</td><td> 
address.country</td><td>String</td><td>no</td><td>Yes</td><td> 
phoneNumber</td><td>String</td><td>no</td><td>Yes</td><td> 
customerNumber</td><td>Integer</td><td>no</td><td>Read-only</td><td> 
customer</td><td>Boolean</td><td>no</td><td>Write-only</td><td>If true, a customer number will be generated if not already present
supplierNumber</td><td>Integer</td><td>no</td><td>Read-only</td><td> 
supplier</td><td>Boolean</td><td>no</td><td>Write-only</td><td>
If true, a supplier number will be generated if not already present

### Examples

#### Creating a customer

    POST 
    {
      "name": "Pianolærer Kamomilla",
      "email": "kamomilla@kardemommeby.com",
      "organizationIdentifier": "987654321",
      "address": {
        "postalCode": "1337",
        "postalPlace": "Kardemommeby",
        "address1": "Tårngata 2"
      },
      "customer": true
    }

#### Listing all contacts
    
    GET 
    {
      "_links": {
        "self": {
          "href": "..."
        }
      },
      "_embedded": {
        "contacts": [
          {
            "_links": {
              "self": {
                "href": "..."
              }
            },
            "name": "Pianolærer Kamomilla",
            "email": "kamomilla@kardemommeby.com",
            "organizationIdentifier": "987654321",
            "address": {
              "postalCode": "1337",
              "postalPlace": "Kardemommeby",
              "address1": "Tårngata 2"
            },
            "customerNumber": "12345"
          },
          {
            "_links": {
              "self": {
                "href": "..."
              }
            },
            "name": "Kasper, Jesper og Jonatans inkassobyrå",
            "email": "kjenning@politimester-sebastian.no",
            "organizationIdentifier": "865723112",
            "address": {
              "postalCode": "2145",
              "postalPlace": "Kardemommeby"
            },
            "supplierNumber": "666"
          }
        ]
      }
    }
    
## Payment

Field
Type
Required
Description
date</td><td>Date</td><td>Yes</td><td>The date when the payment was done
account</td><td>String</td><td>Yes</td><td>
The accounting account that received the payment.
For example "1920:10002"
amount</td><td>Amount</td><td> Yes</td><td> 

### Example

Posting a payment of 123,45.

    {
      "uuid": "...",
      "date": "2001-02-03",
      "account": "1920:10002",
      "amount": 12345
    }
    