# HTTP trace


* [Exchange #1](#tx-1): `GET http://localhost:8080/api/v1` => `200 OK`
* [Exchange #2](#tx-2): `GET http://localhost:8080/api/v1/companies` => `200 OK`
* [Exchange #3](#tx-3): `GET http://localhost:8080/api/v1/companies/test1/sales` => `200 OK`
* [Exchange #4](#tx-4): `GET http://localhost:8080/api/v1/companies/test1/contacts` => `200 OK`
* [Exchange #5](#tx-5): `POST http://localhost:8080/api/v1/companies/test1/sales` => `201 Created`
* [Exchange #6](#tx-6): `GET http://localhost:8080/api/v1/companies/test1/sales/7338071` => `200 OK`
* [Exchange #7](#tx-7): `GET http://localhost:8080/api/v1/companies/test1/sales/7338071/payments` => `200 OK`
* [Exchange #8](#tx-8): `GET http://localhost:8080/api/v1/companies/test1/sales` => `200 OK`
* [Exchange #9](#tx-9): `POST http://localhost:8080/api/v1/companies/test1/sales/7338071/payments` => `201 Created`
* [Exchange #10](#tx-10): `GET http://localhost:8080/api/v1/companies/test1/sales/7338071/payments/7343071` => `200 OK`
* [Exchange #11](#tx-11): `GET http://localhost:8080/api/v1/companies/test1/sales/7338071` => `200 OK`
* [Exchange #12](#tx-12): `POST http://localhost:8080/api/v1/companies/test1/sales/7338071/attachments` => `201 Created`
* [Exchange #13](#tx-13): `GET http://localhost:8080/api/v1/companies/test1/sales/7338071/attachments` => `200 OK`
* [Exchange #14](#tx-14): `POST http://localhost:8080/api/v1/companies/test1/sales` => `201 Created`
* [Exchange #15](#tx-15): `GET http://localhost:8080/api/v1/companies/test1/sales/7338072` => `200 OK`
* [Exchange #16](#tx-16): `GET http://localhost:8080/api/v1/companies/test1/sales` => `200 OK`

<a id='tx-1'>
# Exchange #1
</a>
## Request

    GET http://localhost:8080/api/v1
    Accept: application/hal+json, application/vnd.error+json



## Response 
    200 OK
    Server: Jetty(9.2.5.v20141112)
    Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
    X-Request-ID: 187174bf-6200-4aaa-979f-3f66cace748f
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Content-Length: 149
    Date: Thu, 16 Jul 2015 17:43:35 GMT
    Content-Type: application/hal+json; charset=UTF-8
    
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/v1"
        },
        "https://fiken.no/api/v1/rel/companies": {
          "href": "http://localhost:8080/api/v1/companies"
        }
      }
    }



<a id='tx-2'>
# Exchange #2
</a>
## Request

    GET http://localhost:8080/api/v1/companies
    Accept: application/hal+json, application/vnd.error+json



## Response 
    200 OK
    Server: Jetty(9.2.5.v20141112)
    Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
    X-Request-ID: d8b32e63-fc0b-4e25-9cb3-63b38d726c7c
    Set-Cookie: JSESSIONID=1a0oqo30xz63h41xviw4sovrr;Path=/;HttpOnly
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Content-Length: 916
    Date: Thu, 16 Jul 2015 17:43:36 GMT
    Content-Type: application/hal+json; charset=UTF-8
    
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/v1/companies"
        }
      },
      "_embedded": {
        "https://fiken.no/api/v1/rel/companies": [
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1"
              },
              "https://fiken.no/api/v1/rel/accounts": {
                "href": "http://localhost:8080/api/v1/companies/test1/accounts/{year}",
                "templated": true
              },
              "https://fiken.no/api/v1/rel/bank-accounts": {
                "href": "http://localhost:8080/api/v1/companies/test1/bank-accounts"
              },
              "https://fiken.no/api/v1/rel/contacts": {
                "href": "http://localhost:8080/api/v1/companies/test1/contacts"
              },
              "https://fiken.no/api/v1/rel/invoices": {
                "href": "http://localhost:8080/api/v1/companies/test1/invoices"
              },
              "https://fiken.no/api/v1/rel/products": {
                "href": "http://localhost:8080/api/v1/companies/test1/products"
              },
              "https://fiken.no/api/v1/rel/sales": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales"
              }
            },
            "name": "Test1",
            "slug": "test1",
            "organizationNumber": "123456789"
          }
        ]
      }
    }



<a id='tx-3'>
# Exchange #3
</a>
## Request

    GET http://localhost:8080/api/v1/companies/test1/sales
    Accept: application/hal+json, application/vnd.error+json



## Response 
    200 OK
    Server: Jetty(9.2.5.v20141112)
    Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
    X-Request-ID: 116bcb99-b7b0-4a76-b381-dd87e5f35c94
    Set-Cookie: JSESSIONID=1q73jg3hixgy92hpfv3bbmko2;Path=/;HttpOnly
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Content-Length: 26524
    Date: Thu, 16 Jul 2015 17:43:36 GMT
    Content-Type: application/hal+json; charset=UTF-8
    
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/v1/companies/test1/sales"
        }
      },
      "_embedded": {
        "https://fiken.no/api/v1/rel/sales": [
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7315069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7315069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7315069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7315069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7315069/payments/7319069"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ],
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/7321069"
                    }
                  },
                  "identifier": "21",
                  "downloadUrl": "http://localhost:8080/filer/078167b8-9e5d-45f9-b8ec-ed822138e8c9/yeah.pdf"
                }
              ]
            },
            "date": "2015-01-02",
            "kind": "EXTERNAL_INVOICE",
            "lines": [
              {
                "description": "Maur",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 7312069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/7312069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7326069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7326069/payments/7331069"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ],
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/7333069"
                    }
                  },
                  "identifier": "22",
                  "downloadUrl": "http://localhost:8080/filer/b66848a2-bf2a-4e14-a108-7d4ce1a8325d/yeah.pdf"
                }
              ]
            },
            "date": "2015-01-02",
            "kind": "EXTERNAL_INVOICE",
            "lines": [
              {
                "description": "Maur",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 7312069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/7312069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326070"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326070/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7326070",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7326070/payments/7331070"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ]
            },
            "date": "2015-03-04",
            "kind": "CASH_SALE",
            "lines": [
              {
                "description": "Cola",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ]
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6459072"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6459072/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6459072/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6459072",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6467070"
                    }
                  },
                  "identifier": "2",
                  "downloadUrl": "http://localhost:8080/filer/9b37c34d-e3ac-4988-ae74-3bb57a872e50/faktura_2015_10002.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10002",
            "lines": [
              {
                "description": "my note",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3010",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326071"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326071/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326071/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7326071",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7326071/payments/7331071"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ],
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/7333070"
                    }
                  },
                  "identifier": "23",
                  "downloadUrl": "http://localhost:8080/filer/6e977152-cf5f-4398-b8a3-9e961b78adee/yeah.pdf"
                }
              ]
            },
            "date": "2015-01-02",
            "kind": "EXTERNAL_INVOICE",
            "lines": [
              {
                "description": "Maur",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 7312069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/7312069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326072"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326072/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7326072",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7326072/payments/7331072"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ]
            },
            "date": "2015-03-04",
            "kind": "CASH_SALE",
            "lines": [
              {
                "description": "Cola",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ]
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6472069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6472069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6472069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6472069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6480069"
                    }
                  },
                  "identifier": "3",
                  "downloadUrl": "http://localhost:8080/filer/62f51003-30f5-45ce-a72c-f6ea2852dfaa/faktura_2015_10003.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10003",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3010",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7338069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7338069/payments/7343069"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ],
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/7345069"
                    }
                  },
                  "identifier": "24",
                  "downloadUrl": "http://localhost:8080/filer/62599469-eb0a-4f73-8773-07422603efc1/yeah.pdf"
                }
              ]
            },
            "date": "2015-01-02",
            "kind": "EXTERNAL_INVOICE",
            "lines": [
              {
                "description": "Maur",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 7312069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/7312069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338070"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338070/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7338070",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7338070/payments/7343070"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ]
            },
            "date": "2015-03-04",
            "kind": "CASH_SALE",
            "lines": [
              {
                "description": "Cola",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ]
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6487069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6487069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6487069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6487069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6495069"
                    }
                  },
                  "identifier": "4",
                  "downloadUrl": "http://localhost:8080/filer/3fd7c35f-fd9b-4a44-8956-e003b060729b/faktura_2015_10004.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10004",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3010",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508069"
                    }
                  },
                  "identifier": "5",
                  "downloadUrl": "http://localhost:8080/filer/b4141d92-b664-40ab-87b5-ed4aec59b5d5/faktura_2015_10005.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10005",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3010",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500070"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500070/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500070/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500070",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508070"
                    }
                  },
                  "identifier": "6",
                  "downloadUrl": "http://localhost:8080/filer/42e2bf2d-cbf8-40e9-be9e-0e452746d0cc/faktura_2015_10006.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10006",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3010",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500071"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500071/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500071/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500071",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508071"
                    }
                  },
                  "identifier": "7",
                  "downloadUrl": "http://localhost:8080/filer/ce5ef345-301f-4b4e-837c-e776f534f9e0/faktura_2015_10007.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10007",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500073"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500073/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500073/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500073",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508073"
                    }
                  },
                  "identifier": "9",
                  "downloadUrl": "http://localhost:8080/filer/885bc9b8-e010-437e-ab80-15ec831d2b71/faktura_2015_10009.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10009",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500078"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500078/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500078/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500078",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508078"
                    }
                  },
                  "identifier": "14",
                  "downloadUrl": "http://localhost:8080/filer/0f71e283-a403-43b5-8055-61d2a0695bfd/faktura_2015_10014.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10014",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500072"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500072/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500072/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500072",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508072"
                    }
                  },
                  "identifier": "8",
                  "downloadUrl": "http://localhost:8080/filer/986333b1-728e-4f89-a49e-e04c81de846a/faktura_2015_10008.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10008",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500075"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500075/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500075/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500075",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508075"
                    }
                  },
                  "identifier": "11",
                  "downloadUrl": "http://localhost:8080/filer/d59add8d-2ae3-4293-8719-999461550f40/faktura_2015_10011.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10011",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500083"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500083/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500083/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500083",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508083"
                    }
                  },
                  "identifier": "19",
                  "downloadUrl": "http://localhost:8080/filer/375cebe7-de26-40e9-8f7e-19f87d2ff9db/faktura_2015_10019.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10019",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500074"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500074/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500074/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500074",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508074"
                    }
                  },
                  "identifier": "10",
                  "downloadUrl": "http://localhost:8080/filer/d48d90a7-f051-4287-83f0-6155629746e7/faktura_2015_10010.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10010",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500076"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500076/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500076/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500076",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508076"
                    }
                  },
                  "identifier": "12",
                  "downloadUrl": "http://localhost:8080/filer/6423f3f1-20b0-4b44-a552-8db1aacb7b47/faktura_2015_10012.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10012",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500081"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500081/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500081/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500081",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508081"
                    }
                  },
                  "identifier": "17",
                  "downloadUrl": "http://localhost:8080/filer/2fe2e2f9-442c-4b89-981a-2503dcae701c/faktura_2015_10017.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10017",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500077"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500077/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500077/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500077",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508077"
                    }
                  },
                  "identifier": "13",
                  "downloadUrl": "http://localhost:8080/filer/568f368c-c9d3-4f34-bf35-2c28d34b116a/faktura_2015_10013.pdf",
                  "kind": "INVOICE"
                },
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508084"
                    }
                  },
                  "identifier": "20",
                  "downloadUrl": "http://localhost:8080/filer/768bfc4a-7c5a-4e73-9d40-09918e40c73d/faktura_2015_10013_purring.pdf",
                  "kind": "REMINDER"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10013",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              },
              {
                "description": "Purregebyr 13.04.2015",
                "netPrice": 6500,
                "vat": 0,
                "incomeAccount": "3900",
                "vatType": "NONE"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500079"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500079/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500079/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500079",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508079"
                    }
                  },
                  "identifier": "15",
                  "downloadUrl": "http://localhost:8080/filer/a49b9cb5-d33d-4336-a0f3-c03cb307b2ba/faktura_2015_10015.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10015",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500080"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500080/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500080/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500080",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508080"
                    }
                  },
                  "identifier": "16",
                  "downloadUrl": "http://localhost:8080/filer/6a32336d-9542-4b64-becd-96cf45f3566b/faktura_2015_10016.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10016",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500082"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500082/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500082/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500082",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508082"
                    }
                  },
                  "identifier": "18",
                  "downloadUrl": "http://localhost:8080/filer/87b6cf52-8acf-402f-8ec4-39ca821b0bdf/faktura_2015_10018.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10018",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6412070"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6412070/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6412070/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6412070",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/6412070/payments/6417069"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ]
            },
            "date": "2015-01-02",
            "kind": "EXTERNAL_INVOICE",
            "identifier": "abc123",
            "lines": [
              {
                "description": "Description",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6409069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6409069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6427069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6427069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6427069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6427069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6434069"
                    }
                  },
                  "identifier": "1",
                  "downloadUrl": "http://localhost:8080/filer/42e46462-f58a-4f6b-8e4f-9b86232e02c0/faktura_2015_10001.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-04-11",
            "kind": "INVOICE",
            "identifier": "10001",
            "lines": [
              {
                "description": "Maur",
                "netPrice": 1000,
                "vat": 0,
                "incomeAccount": "3010",
                "vatType": "NONE"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          }
        ]
      }
    }



<a id='tx-4'>
# Exchange #4
</a>
## Request

    GET http://localhost:8080/api/v1/companies/test1/contacts
    Accept: application/hal+json, application/vnd.error+json



## Response 
    200 OK
    Server: Jetty(9.2.5.v20141112)
    Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
    X-Request-ID: 0ecda824-5761-434e-8647-30605c23486e
    Set-Cookie: JSESSIONID=i6refsvz35wl4vhl13ng5o3l;Path=/;HttpOnly
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Content-Length: 977
    Date: Thu, 16 Jul 2015 17:43:44 GMT
    Content-Type: application/hal+json; charset=UTF-8
    
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/v1/companies/test1/contacts"
        }
      },
      "_embedded": {
        "https://fiken.no/api/v1/rel/contacts": [
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
              }
            },
            "name": "Fakturakunde AS",
            "address": {
              "country": "Norge"
            },
            "customerNumber": 10002
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/contacts/6409069"
              }
            },
            "name": "Maurspiserene AS",
            "address": {
              "country": "Norge"
            },
            "customerNumber": 10001
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/contacts/7312069"
              }
            },
            "name": "Scenario test customer",
            "email": "test1@fiken.no",
            "address": {
              "country": "Norge"
            },
            "customerNumber": 10003
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/contacts/6400069"
              }
            },
            "name": "Watchcom Security Group AS",
            "organizationIdentifier": "987020881",
            "address": {
              "country": "Norge",
              "address1": "Postboks 1861 Vika",
              "postalPlace": "OSLO",
              "postalCode": "0124"
            },
            "supplierNumber": 20001
          }
        ]
      }
    }



<a id='tx-5'>
# Exchange #5
</a>
## Request

    POST http://localhost:8080/api/v1/companies/test1/sales
    Content-Type: application/hal+json
    Accept: application/hal+json, application/vnd.error+json

    {
      "customer": "http://localhost:8080/api/v1/companies/test1/contacts/7312069",
      "kind": "EXTERNAL_INVOICE",
      "date": "2015-01-02",
      "lines": [
        {
          "netPrice": 100,
          "vat": 25,
          "description": "Maur",
          "vatType": "HIGH"
        }
      ]
    }


## Response 
    201 Created
    Server: Jetty(9.2.5.v20141112)
    Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
    X-Request-ID: 4548bec8-05cd-4e6a-a97a-e0c0207845ed
    Set-Cookie: JSESSIONID=3y5jrfurvc6ygv959ewup1pk;Path=/;HttpOnly
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Content-Length: 0
    Date: Thu, 16 Jul 2015 17:43:44 GMT
    Location: http://localhost:8080/api/v1/companies/test1/sales/7338071
    
    null



<a id='tx-6'>
# Exchange #6
</a>
## Request

    GET http://localhost:8080/api/v1/companies/test1/sales/7338071
    Accept: application/hal+json, application/vnd.error+json



## Response 
    200 OK
    Server: Jetty(9.2.5.v20141112)
    Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
    X-Request-ID: 97aa9806-a439-45b4-b17b-d0d6667633b6
    Set-Cookie: JSESSIONID=xayejv6c52bh1qw1qet2vhds0;Path=/;HttpOnly
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Content-Length: 673
    Date: Thu, 16 Jul 2015 17:43:44 GMT
    Content-Type: application/hal+json; charset=UTF-8
    
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/v1/companies/test1/sales/7338071"
        },
        "https://fiken.no/api/v1/rel/payments": {
          "href": "http://localhost:8080/api/v1/companies/test1/sales/7338071/payments"
        },
        "https://fiken.no/api/v1/rel/attachments": {
          "href": "http://localhost:8080/api/v1/companies/test1/sales/7338071/attachments"
        },
        "alternate": {
          "href": "http://localhost:8080/foretak/test1/handel/#/salg/7338071",
          "type": "text/html"
        }
      },
      "date": "2015-01-02",
      "kind": "EXTERNAL_INVOICE",
      "lines": [
        {
          "description": "Maur",
          "netPrice": 100,
          "vat": 25,
          "incomeAccount": "3000",
          "vatType": "HIGH"
        }
      ],
      "customer": {
        "value": 7312069,
        "url": "http://localhost:8080/api/v1/companies/test1/contacts/7312069"
      }
    }



<a id='tx-7'>
# Exchange #7
</a>
## Request

    GET http://localhost:8080/api/v1/companies/test1/sales/7338071/payments
    Accept: application/hal+json, application/vnd.error+json



## Response 
    200 OK
    Server: Jetty(9.2.5.v20141112)
    Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
    X-Request-ID: f7b22085-24c1-41d2-b9e8-babae54495b6
    Set-Cookie: JSESSIONID=xpb336rk0yu51rx69u2sb30qy;Path=/;HttpOnly
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Content-Length: 98
    Date: Thu, 16 Jul 2015 17:43:44 GMT
    Content-Type: application/hal+json; charset=UTF-8
    
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/v1/companies/test1/sales/7338071/payments"
        }
      }
    }



<a id='tx-8'>
# Exchange #8
</a>
## Request

    GET http://localhost:8080/api/v1/companies/test1/sales
    Accept: application/hal+json, application/vnd.error+json



## Response 
    200 OK
    Server: Jetty(9.2.5.v20141112)
    Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
    X-Request-ID: 96833dcd-3b6b-4856-a0cf-ac7985c62b74
    Set-Cookie: JSESSIONID=6eoikl08rykq1e17u6rgrqq0f;Path=/;HttpOnly
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Content-Length: 27198
    Date: Thu, 16 Jul 2015 17:43:44 GMT
    Content-Type: application/hal+json; charset=UTF-8
    
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/v1/companies/test1/sales"
        }
      },
      "_embedded": {
        "https://fiken.no/api/v1/rel/sales": [
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7315069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7315069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7315069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7315069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7315069/payments/7319069"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ],
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/7321069"
                    }
                  },
                  "identifier": "21",
                  "downloadUrl": "http://localhost:8080/filer/078167b8-9e5d-45f9-b8ec-ed822138e8c9/yeah.pdf"
                }
              ]
            },
            "date": "2015-01-02",
            "kind": "EXTERNAL_INVOICE",
            "lines": [
              {
                "description": "Maur",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 7312069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/7312069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7326069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7326069/payments/7331069"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ],
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/7333069"
                    }
                  },
                  "identifier": "22",
                  "downloadUrl": "http://localhost:8080/filer/b66848a2-bf2a-4e14-a108-7d4ce1a8325d/yeah.pdf"
                }
              ]
            },
            "date": "2015-01-02",
            "kind": "EXTERNAL_INVOICE",
            "lines": [
              {
                "description": "Maur",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 7312069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/7312069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326070"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326070/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7326070",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7326070/payments/7331070"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ]
            },
            "date": "2015-03-04",
            "kind": "CASH_SALE",
            "lines": [
              {
                "description": "Cola",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ]
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6459072"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6459072/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6459072/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6459072",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6467070"
                    }
                  },
                  "identifier": "2",
                  "downloadUrl": "http://localhost:8080/filer/9b37c34d-e3ac-4988-ae74-3bb57a872e50/faktura_2015_10002.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10002",
            "lines": [
              {
                "description": "my note",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3010",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326071"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326071/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326071/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7326071",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7326071/payments/7331071"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ],
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/7333070"
                    }
                  },
                  "identifier": "23",
                  "downloadUrl": "http://localhost:8080/filer/6e977152-cf5f-4398-b8a3-9e961b78adee/yeah.pdf"
                }
              ]
            },
            "date": "2015-01-02",
            "kind": "EXTERNAL_INVOICE",
            "lines": [
              {
                "description": "Maur",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 7312069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/7312069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326072"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326072/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7326072",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7326072/payments/7331072"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ]
            },
            "date": "2015-03-04",
            "kind": "CASH_SALE",
            "lines": [
              {
                "description": "Cola",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ]
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6472069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6472069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6472069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6472069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6480069"
                    }
                  },
                  "identifier": "3",
                  "downloadUrl": "http://localhost:8080/filer/62f51003-30f5-45ce-a72c-f6ea2852dfaa/faktura_2015_10003.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10003",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3010",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7338069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7338069/payments/7343069"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ],
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/7345069"
                    }
                  },
                  "identifier": "24",
                  "downloadUrl": "http://localhost:8080/filer/62599469-eb0a-4f73-8773-07422603efc1/yeah.pdf"
                }
              ]
            },
            "date": "2015-01-02",
            "kind": "EXTERNAL_INVOICE",
            "lines": [
              {
                "description": "Maur",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 7312069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/7312069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338070"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338070/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7338070",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7338070/payments/7343070"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ]
            },
            "date": "2015-03-04",
            "kind": "CASH_SALE",
            "lines": [
              {
                "description": "Cola",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ]
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6487069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6487069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6487069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6487069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6495069"
                    }
                  },
                  "identifier": "4",
                  "downloadUrl": "http://localhost:8080/filer/3fd7c35f-fd9b-4a44-8956-e003b060729b/faktura_2015_10004.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10004",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3010",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338071"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338071/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338071/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7338071",
                "type": "text/html"
              }
            },
            "date": "2015-01-02",
            "kind": "EXTERNAL_INVOICE",
            "lines": [
              {
                "description": "Maur",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 7312069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/7312069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508069"
                    }
                  },
                  "identifier": "5",
                  "downloadUrl": "http://localhost:8080/filer/b4141d92-b664-40ab-87b5-ed4aec59b5d5/faktura_2015_10005.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10005",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3010",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500070"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500070/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500070/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500070",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508070"
                    }
                  },
                  "identifier": "6",
                  "downloadUrl": "http://localhost:8080/filer/42e2bf2d-cbf8-40e9-be9e-0e452746d0cc/faktura_2015_10006.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10006",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3010",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500071"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500071/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500071/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500071",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508071"
                    }
                  },
                  "identifier": "7",
                  "downloadUrl": "http://localhost:8080/filer/ce5ef345-301f-4b4e-837c-e776f534f9e0/faktura_2015_10007.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10007",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500073"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500073/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500073/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500073",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508073"
                    }
                  },
                  "identifier": "9",
                  "downloadUrl": "http://localhost:8080/filer/885bc9b8-e010-437e-ab80-15ec831d2b71/faktura_2015_10009.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10009",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500078"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500078/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500078/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500078",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508078"
                    }
                  },
                  "identifier": "14",
                  "downloadUrl": "http://localhost:8080/filer/0f71e283-a403-43b5-8055-61d2a0695bfd/faktura_2015_10014.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10014",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500072"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500072/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500072/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500072",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508072"
                    }
                  },
                  "identifier": "8",
                  "downloadUrl": "http://localhost:8080/filer/986333b1-728e-4f89-a49e-e04c81de846a/faktura_2015_10008.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10008",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500075"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500075/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500075/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500075",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508075"
                    }
                  },
                  "identifier": "11",
                  "downloadUrl": "http://localhost:8080/filer/d59add8d-2ae3-4293-8719-999461550f40/faktura_2015_10011.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10011",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500083"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500083/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500083/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500083",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508083"
                    }
                  },
                  "identifier": "19",
                  "downloadUrl": "http://localhost:8080/filer/375cebe7-de26-40e9-8f7e-19f87d2ff9db/faktura_2015_10019.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10019",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500074"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500074/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500074/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500074",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508074"
                    }
                  },
                  "identifier": "10",
                  "downloadUrl": "http://localhost:8080/filer/d48d90a7-f051-4287-83f0-6155629746e7/faktura_2015_10010.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10010",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500076"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500076/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500076/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500076",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508076"
                    }
                  },
                  "identifier": "12",
                  "downloadUrl": "http://localhost:8080/filer/6423f3f1-20b0-4b44-a552-8db1aacb7b47/faktura_2015_10012.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10012",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500081"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500081/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500081/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500081",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508081"
                    }
                  },
                  "identifier": "17",
                  "downloadUrl": "http://localhost:8080/filer/2fe2e2f9-442c-4b89-981a-2503dcae701c/faktura_2015_10017.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10017",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500077"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500077/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500077/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500077",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508077"
                    }
                  },
                  "identifier": "13",
                  "downloadUrl": "http://localhost:8080/filer/568f368c-c9d3-4f34-bf35-2c28d34b116a/faktura_2015_10013.pdf",
                  "kind": "INVOICE"
                },
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508084"
                    }
                  },
                  "identifier": "20",
                  "downloadUrl": "http://localhost:8080/filer/768bfc4a-7c5a-4e73-9d40-09918e40c73d/faktura_2015_10013_purring.pdf",
                  "kind": "REMINDER"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10013",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              },
              {
                "description": "Purregebyr 13.04.2015",
                "netPrice": 6500,
                "vat": 0,
                "incomeAccount": "3900",
                "vatType": "NONE"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500079"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500079/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500079/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500079",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508079"
                    }
                  },
                  "identifier": "15",
                  "downloadUrl": "http://localhost:8080/filer/a49b9cb5-d33d-4336-a0f3-c03cb307b2ba/faktura_2015_10015.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10015",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500080"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500080/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500080/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500080",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508080"
                    }
                  },
                  "identifier": "16",
                  "downloadUrl": "http://localhost:8080/filer/6a32336d-9542-4b64-becd-96cf45f3566b/faktura_2015_10016.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10016",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500082"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500082/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500082/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500082",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508082"
                    }
                  },
                  "identifier": "18",
                  "downloadUrl": "http://localhost:8080/filer/87b6cf52-8acf-402f-8ec4-39ca821b0bdf/faktura_2015_10018.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10018",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6412070"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6412070/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6412070/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6412070",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/6412070/payments/6417069"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ]
            },
            "date": "2015-01-02",
            "kind": "EXTERNAL_INVOICE",
            "identifier": "abc123",
            "lines": [
              {
                "description": "Description",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6409069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6409069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6427069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6427069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6427069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6427069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6434069"
                    }
                  },
                  "identifier": "1",
                  "downloadUrl": "http://localhost:8080/filer/42e46462-f58a-4f6b-8e4f-9b86232e02c0/faktura_2015_10001.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-04-11",
            "kind": "INVOICE",
            "identifier": "10001",
            "lines": [
              {
                "description": "Maur",
                "netPrice": 1000,
                "vat": 0,
                "incomeAccount": "3010",
                "vatType": "NONE"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          }
        ]
      }
    }



<a id='tx-9'>
# Exchange #9
</a>
## Request

    POST http://localhost:8080/api/v1/companies/test1/sales/7338071/payments
    Content-Type: application/hal+json
    Accept: application/hal+json, application/vnd.error+json

    {
      "date": "2015-01-03",
      "account": "1920:10001",
      "amount": 125
    }


## Response 
    201 Created
    Server: Jetty(9.2.5.v20141112)
    Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
    X-Request-ID: ff4bfc29-c7f5-4770-bd7e-8b209e41ac4d
    Set-Cookie: JSESSIONID=srvmogapgbul1tcubv1352ngn;Path=/;HttpOnly
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Content-Length: 0
    Date: Thu, 16 Jul 2015 17:43:44 GMT
    Location: http://localhost:8080/api/v1/companies/test1/sales/7338071/payments/7343071
    
    null



<a id='tx-10'>
# Exchange #10
</a>
## Request

    GET http://localhost:8080/api/v1/companies/test1/sales/7338071/payments/7343071
    Accept: application/hal+json, application/vnd.error+json



## Response 
    200 OK
    Server: Jetty(9.2.5.v20141112)
    Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
    X-Request-ID: a014dc2f-2e26-4e75-bf66-cea303ce1981
    Set-Cookie: JSESSIONID=1xhleq16ql7u318avutqrc15vq;Path=/;HttpOnly
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Content-Length: 142
    Date: Thu, 16 Jul 2015 17:43:45 GMT
    Content-Type: application/hal+json; charset=UTF-8
    
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/v1/companies/test1/sales/7338071/payments/7343071"
        }
      },
      "amount": 125,
      "account": "1920:10001"
    }



<a id='tx-11'>
# Exchange #11
</a>
## Request

    GET http://localhost:8080/api/v1/companies/test1/sales/7338071
    Accept: application/hal+json, application/vnd.error+json



## Response 
    200 OK
    Server: Jetty(9.2.5.v20141112)
    Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
    X-Request-ID: 713d950a-b103-4181-8f26-d0f02d040b30
    Set-Cookie: JSESSIONID=1d0cewreu60le9cjifnfdd4b0;Path=/;HttpOnly
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Content-Length: 871
    Date: Thu, 16 Jul 2015 17:43:52 GMT
    Content-Type: application/hal+json; charset=UTF-8
    
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/v1/companies/test1/sales/7338071"
        },
        "https://fiken.no/api/v1/rel/payments": {
          "href": "http://localhost:8080/api/v1/companies/test1/sales/7338071/payments"
        },
        "https://fiken.no/api/v1/rel/attachments": {
          "href": "http://localhost:8080/api/v1/companies/test1/sales/7338071/attachments"
        },
        "alternate": {
          "href": "http://localhost:8080/foretak/test1/handel/#/salg/7338071",
          "type": "text/html"
        }
      },
      "_embedded": {
        "https://fiken.no/api/v1/rel/payments": [
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338071/payments/7343071"
              }
            },
            "amount": 125,
            "account": "1920:10001"
          }
        ]
      },
      "date": "2015-01-02",
      "kind": "EXTERNAL_INVOICE",
      "lines": [
        {
          "description": "Maur",
          "netPrice": 100,
          "vat": 25,
          "incomeAccount": "3000",
          "vatType": "HIGH"
        }
      ],
      "customer": {
        "value": 7312069,
        "url": "http://localhost:8080/api/v1/companies/test1/contacts/7312069"
      }
    }



<a id='tx-12'>
# Exchange #12
</a>
## Request

    POST http://localhost:8080/api/v1/companies/test1/sales/7338071/attachments
    Content-Type: multipart/form-data;boundary=----=_Part_0_835227336.1437068632932

    ------=_Part_0_835227336.1437068632932
    Content-Type: application/json
    Content-Disposition: form-data; name="SaleAttachment"
    
    {
      "attachToSale": false,
      "attachToPayment": true,
      "filename": "yeah.pdf"
    }
    ------=_Part_0_835227336.1437068632932
    Content-Type: application/pdf
    Content-Disposition: form-data; name="AttachmentFile"
    
    %PDF-1.4
    %ÈÁÄ×
    8 0 obj
    <<
    /Filter /FlateDecode 
    /Length 121 
    >> stream
    x�U��
    �0E�~�ӡ��䵫 ���M�Lu�3��[A.�{�px"~�����}I�u'a�F:�53sj�%6�\^hO�Qd���(�d"=��KYZ��FܻE�-u�\��ұ�}j>�6 
    endstream
    endobj
    6 0 obj
    <<
    /Group <<
    /S /Transparency /K true /I true /CS 4 0 R  >>  
    /Contents 8 0 R  
    /Resources <<
    /Font <<
    /Helv 9 0 R  >>  /ColorSpace <<
    /DefaultRGB 4 0 R  >>  /ProcSet [/PDF /Text] >>  
    /Type /Page 
    /Parent 7 0 R  
    /MediaBox [0 0 595.238 841.836] 
    >> endobj
    7 0 obj
    <<
    /Kids [6 0 R] 
    /Type /Pages 
    /Count 1 
    /Parent 2 0 R  
    >> endobj
    2 0 obj
    <<
    /Kids [7 0 R] 
    /Type /Pages 
    /Count 1 
    >> endobj
    1 0 obj
    <<
    /Outlines 3 0 R  
    /Pages 2 0 R  
    /Type /Catalog 
    /PageMode /UseNone 
    >> endobj
    4 0 obj
    [/ICCBased 5 0 R ] 
    endobj
    5 0 obj
    <<
    /Filter /FlateDecode 
    /N 3 
    /Length 2602 
    >> stream
    x�
    ��x���gTT��Ͻwz��0tz�m �I��2�0��ņ�
    Di� AFC�X�BPT�� ��`QQy3�V���{/��ý�����]�Z �O ��K���C����1�t� �`�9 LVVf`�W8��ӝ�%r���# �o�����I���  �ؒ��b��@�i9�L�}V�ԄT1�(1�E����>��'���"fv:�-b�3��l1��x[��#b$@ą�\N��o�X+M���ql:�� �$�8�d����u� p��/8�pV�I�gd��I��K�nngǠ�pr�8�q0�������L^. �s�$qm�"ۘ���[��Q����%��gz�gm�w۟�e4 ���f�ﶄ* ��  �w�� $E}��E>4�$����999&\�D\���������x���C��$2�i��n���!����dq������0
    �$r��("R4e\^���<6W���ѹ�����}�k�(�u�	��F�� E!$n�h��o�H �yQj��������.?��I���C��,!?���Z4  I@
    @h=`,�-p .��� b�
    �� �A��@!(;�P
�@#hm��'�9p\��0�F�xf�k� A"CHR��!C�b@N�' �B1P<�� !�m���2�����o��9�24݅Ơi�W���$�
    ��:�)̀]a8^'���5p����#p'|�
    ã�3x�!�1�@ܑ $ID��z��@�6��Gn"����AQPt�1�僊@�P�P�Q%�j�aT'�u5��E}D���hC�=��NB���&t�z=�~��`h]�-��I��Ŕ`�a�1g1C�q��U�b�AX&V�-�Va�`�`o`'�opD��煋��p��
    \�4�n����k���Ax6>_�o����'�i�.��NH!l"T�/�D�юB�7+�G���cķ$�ɝG�v��Β�^��d�9�, � 7�ϓ��HP$L$|%�$j$:%nH<��KjK�J��\#Y!y\��^JG�]�)�^�F��m�9i���t�t�t�t��e�)�����[�@��y�q
    BѤ�SX�͔F��Cե�RS���o���YYY+�H�ղ5��dGiM��KK��Ҏ�Fh��T�\�8r����n���+ɻ�s������)�<Rv)t)<TD)(�(�(�W��8�DUrPb))S��+(�*�U>�<�<���⭒�R�r^eF��ꢚ�Z�zZuZ����U+W;���.Kw���+�}�Yueuu�z�������F�F�F��CM�&C3Q�\�WsVKM+P+O�U�6^�����W�_{^GW'Jg�N�Δ������V�zd=g�Uz
z��1��T�}��
`k�d��k����!�p����Έg�`tۘd�j�m�j<fB3	0�7�2yn�ek�˴�����Y�Y��}ss?�|��_-,X5�,ɖ^�,�-_XZq��[ݱ�XZo���`ck÷i���ղ�����͠2�%�Kvh;7�
v'���������`����0�Dw	gI�qG
G�c���)��Ө��3ӹ�����ۥ�e�U�5����s737�[�ۼ���:�����G�Ǡ��g�g��#/
�$�V�Yok��g}�>�>�|n����|�}g�l������������z�@��݁�j/�-�
    A�A����
    �>R�$�<4/�?��2�%�u�[xi���aDo�dd\ds�|�GTY�h�i���1�1ܘ�XlldlS��2�e{�M�Y�ƍ,�]�z���+�V�Z)����x<:>*�%�=3����K�M�M�e������]���i�#��3��X�8�䘴;i:�9�"y��έ�H�I�K�O
J=����֞�K�O?�����2T3Vgeff���_�g�,ߟߔe-��PE?SB=��X�SvM���Ȝ㫥W�V��nϝ\��뵨����y�y���ֹ��_�OX߻AsC�����o"lJ��C�Y~Y���Q�{
    T
    6�o���Z(Q�/���ak�6�6���۫�,b])6+�(~_�*���W�_-�H�1XjS�'f'o��.�]�ˤ�֔����YN//*�g��Vu{	{�{G+*����vV��N��q�i�U��^;�����~��mu*u�u�pܩ���l�i�8�9�}�Icdc�׌������>�=z��ٶ��E���n�N�;r��o�ی���i��G�Q�ѧ��;r��X�q��﴿��tuB����]�]��1�C'�N��8�t|o�����'kNɞ*=M8]pz�̚3sg3�ΜK:7޻�������B�/�_�t����~��3�/��l��ƕ��6W;�:~���c�f�����v�{�����|��M��o�޺:�txh$b���ۣw�w���}q/�������J=�x����G��GmFO�y�
<{|�5�짬��O<!?��T�l���:9�5}�鲧�2�-��,�s�s��������l������_K^*�<���U�\�ܣ����(�9����]ԻɅ������?�|���`1}q�_�����b	A
    endstream
    endobj
    3 0 obj
    <<
    >> endobj
    9 0 obj
    <<
    /BaseFont /Helvetica 
    /Encoding /WinAnsiEncoding 
    /Subtype /Type1 
    /Type /Font 
    /Name /Helv 
    >> endobj
    10 0 obj
    <<
    /CreationDate (D:20080611165603) 
    /ModDate (D:20080611165603) 
    /Producer (Ibex PDF Creator 4.3.6.4/5025 [.NET 2.0]) 
    >> endobj
    xref
    0 11
    0000000000 65535 f 
    0000000616 00000 n 
    0000000556 00000 n 
    0000003424 00000 n 
    0000000706 00000 n 
    0000000741 00000 n 
    0000000215 00000 n 
    0000000480 00000 n 
    0000000020 00000 n 
    0000003445 00000 n 
    0000003559 00000 n 
    trailer
    <<
    /Size 11 
    /Info 10 0 R  
    /Root 1 0 R 
    >>
    startxref
    3698
    %%EOF
    
    ------=_Part_0_835227336.1437068632932--


## Response 
    201 Created
    Server: Jetty(9.2.5.v20141112)
    Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
    X-Request-ID: e5f6a5eb-c64e-4b1e-a801-39cef5562be5
    Set-Cookie: JSESSIONID=a85dm5mj99xfwtvu37bc2nad;Path=/;HttpOnly
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Content-Length: 0
    Date: Thu, 16 Jul 2015 17:43:54 GMT
    Location: http://localhost:8080/api/v1/companies/test1/sales/7338071/attachments
    
    null



<a id='tx-13'>
# Exchange #13
</a>
## Request

    GET http://localhost:8080/api/v1/companies/test1/sales/7338071/attachments
    Accept: application/hal+json, application/vnd.error+json



## Response 
    200 OK
    Server: Jetty(9.2.5.v20141112)
    Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
    X-Request-ID: c84e3c25-4505-4401-9162-61fed7b398ae
    Set-Cookie: JSESSIONID=30huyp640afp1tfmcebkdc72e;Path=/;HttpOnly
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Content-Length: 363
    Date: Thu, 16 Jul 2015 17:43:54 GMT
    Content-Type: application/hal+json; charset=UTF-8
    
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/v1/companies/test1/sales/7338071/attachments"
        }
      },
      "_embedded": {
        "https://fiken.no/api/v1/rel/attachments": [
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/attachments/7345070"
              }
            },
            "identifier": "25",
            "downloadUrl": "http://localhost:8080/filer/af5fb10a-039e-4a6f-919e-4901c693576d/yeah.pdf"
          }
        ]
      }
    }



<a id='tx-14'>
# Exchange #14
</a>
## Request

    POST http://localhost:8080/api/v1/companies/test1/sales
    Content-Type: application/hal+json
    Accept: application/hal+json, application/vnd.error+json

    {
      "kind": "CASH_SALE",
      "date": "2015-03-04",
      "paymentDate": "2015-03-04",
      "paymentAccount": "1920:10001",
      "lines": [
        {
          "vatType": "HIGH",
          "netPrice": 100,
          "vat": 25,
          "description": "Cola"
        }
      ]
    }


## Response 
    201 Created
    Server: Jetty(9.2.5.v20141112)
    Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
    X-Request-ID: 1cb65335-b99c-4e4e-bf2b-fee75c16e61f
    Set-Cookie: JSESSIONID=tqgoqm1bkzgu1tok8ufqx7rc0;Path=/;HttpOnly
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Content-Length: 0
    Date: Thu, 16 Jul 2015 17:43:54 GMT
    Location: http://localhost:8080/api/v1/companies/test1/sales/7338072
    
    null



<a id='tx-15'>
# Exchange #15
</a>
## Request

    GET http://localhost:8080/api/v1/companies/test1/sales/7338072
    Accept: application/hal+json, application/vnd.error+json



## Response 
    200 OK
    Server: Jetty(9.2.5.v20141112)
    Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
    X-Request-ID: 1996f137-2a70-4729-9a82-40c12e125adc
    Set-Cookie: JSESSIONID=14f599gonwrcs1fguuxa92jhtv;Path=/;HttpOnly
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Content-Length: 647
    Date: Thu, 16 Jul 2015 17:43:55 GMT
    Content-Type: application/hal+json; charset=UTF-8
    
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/v1/companies/test1/sales/7338072"
        },
        "https://fiken.no/api/v1/rel/attachments": {
          "href": "http://localhost:8080/api/v1/companies/test1/sales/7338072/attachments"
        },
        "alternate": {
          "href": "http://localhost:8080/foretak/test1/handel/#/salg/7338072",
          "type": "text/html"
        }
      },
      "_embedded": {
        "https://fiken.no/api/v1/rel/payments": [
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338072/payments/7343072"
              }
            },
            "amount": 125,
            "account": "1920:10001"
          }
        ]
      },
      "date": "2015-03-04",
      "kind": "CASH_SALE",
      "lines": [
        {
          "description": "Cola",
          "netPrice": 100,
          "vat": 25,
          "incomeAccount": "3000",
          "vatType": "HIGH"
        }
      ]
    }



<a id='tx-16'>
# Exchange #16
</a>
## Request

    GET http://localhost:8080/api/v1/companies/test1/sales
    Accept: application/hal+json, application/vnd.error+json



## Response 
    200 OK
    Server: Jetty(9.2.5.v20141112)
    Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
    X-Request-ID: 61c4aa2a-598e-486a-b001-a6a16b395836
    Set-Cookie: JSESSIONID=j94r3dain1rt1utp848rv5xzc;Path=/;HttpOnly
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Content-Length: 28292
    Date: Thu, 16 Jul 2015 17:43:55 GMT
    Content-Type: application/hal+json; charset=UTF-8
    
    {
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/v1/companies/test1/sales"
        }
      },
      "_embedded": {
        "https://fiken.no/api/v1/rel/sales": [
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7315069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7315069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7315069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7315069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7315069/payments/7319069"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ],
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/7321069"
                    }
                  },
                  "identifier": "21",
                  "downloadUrl": "http://localhost:8080/filer/078167b8-9e5d-45f9-b8ec-ed822138e8c9/yeah.pdf"
                }
              ]
            },
            "date": "2015-01-02",
            "kind": "EXTERNAL_INVOICE",
            "lines": [
              {
                "description": "Maur",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 7312069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/7312069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7326069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7326069/payments/7331069"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ],
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/7333069"
                    }
                  },
                  "identifier": "22",
                  "downloadUrl": "http://localhost:8080/filer/b66848a2-bf2a-4e14-a108-7d4ce1a8325d/yeah.pdf"
                }
              ]
            },
            "date": "2015-01-02",
            "kind": "EXTERNAL_INVOICE",
            "lines": [
              {
                "description": "Maur",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 7312069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/7312069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326070"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326070/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7326070",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7326070/payments/7331070"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ]
            },
            "date": "2015-03-04",
            "kind": "CASH_SALE",
            "lines": [
              {
                "description": "Cola",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ]
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6459072"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6459072/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6459072/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6459072",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6467070"
                    }
                  },
                  "identifier": "2",
                  "downloadUrl": "http://localhost:8080/filer/9b37c34d-e3ac-4988-ae74-3bb57a872e50/faktura_2015_10002.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10002",
            "lines": [
              {
                "description": "my note",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3010",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326071"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326071/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326071/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7326071",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7326071/payments/7331071"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ],
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/7333070"
                    }
                  },
                  "identifier": "23",
                  "downloadUrl": "http://localhost:8080/filer/6e977152-cf5f-4398-b8a3-9e961b78adee/yeah.pdf"
                }
              ]
            },
            "date": "2015-01-02",
            "kind": "EXTERNAL_INVOICE",
            "lines": [
              {
                "description": "Maur",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 7312069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/7312069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326072"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7326072/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7326072",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7326072/payments/7331072"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ]
            },
            "date": "2015-03-04",
            "kind": "CASH_SALE",
            "lines": [
              {
                "description": "Cola",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ]
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6472069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6472069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6472069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6472069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6480069"
                    }
                  },
                  "identifier": "3",
                  "downloadUrl": "http://localhost:8080/filer/62f51003-30f5-45ce-a72c-f6ea2852dfaa/faktura_2015_10003.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10003",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3010",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7338069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7338069/payments/7343069"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ],
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/7345069"
                    }
                  },
                  "identifier": "24",
                  "downloadUrl": "http://localhost:8080/filer/62599469-eb0a-4f73-8773-07422603efc1/yeah.pdf"
                }
              ]
            },
            "date": "2015-01-02",
            "kind": "EXTERNAL_INVOICE",
            "lines": [
              {
                "description": "Maur",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 7312069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/7312069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338070"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338070/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7338070",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7338070/payments/7343070"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ]
            },
            "date": "2015-03-04",
            "kind": "CASH_SALE",
            "lines": [
              {
                "description": "Cola",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ]
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6487069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6487069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6487069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6487069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6495069"
                    }
                  },
                  "identifier": "4",
                  "downloadUrl": "http://localhost:8080/filer/3fd7c35f-fd9b-4a44-8956-e003b060729b/faktura_2015_10004.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10004",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3010",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338071"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338071/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338071/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7338071",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7338071/payments/7343071"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ],
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/7345070"
                    }
                  },
                  "identifier": "25",
                  "downloadUrl": "http://localhost:8080/filer/af5fb10a-039e-4a6f-919e-4901c693576d/yeah.pdf"
                }
              ]
            },
            "date": "2015-01-02",
            "kind": "EXTERNAL_INVOICE",
            "lines": [
              {
                "description": "Maur",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 7312069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/7312069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508069"
                    }
                  },
                  "identifier": "5",
                  "downloadUrl": "http://localhost:8080/filer/b4141d92-b664-40ab-87b5-ed4aec59b5d5/faktura_2015_10005.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10005",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3010",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500070"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500070/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500070/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500070",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508070"
                    }
                  },
                  "identifier": "6",
                  "downloadUrl": "http://localhost:8080/filer/42e2bf2d-cbf8-40e9-be9e-0e452746d0cc/faktura_2015_10006.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10006",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3010",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338072"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/7338072/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/7338072",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/7338072/payments/7343072"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ]
            },
            "date": "2015-03-04",
            "kind": "CASH_SALE",
            "lines": [
              {
                "description": "Cola",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ]
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500071"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500071/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500071/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500071",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508071"
                    }
                  },
                  "identifier": "7",
                  "downloadUrl": "http://localhost:8080/filer/ce5ef345-301f-4b4e-837c-e776f534f9e0/faktura_2015_10007.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10007",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500073"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500073/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500073/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500073",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508073"
                    }
                  },
                  "identifier": "9",
                  "downloadUrl": "http://localhost:8080/filer/885bc9b8-e010-437e-ab80-15ec831d2b71/faktura_2015_10009.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10009",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500078"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500078/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500078/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500078",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508078"
                    }
                  },
                  "identifier": "14",
                  "downloadUrl": "http://localhost:8080/filer/0f71e283-a403-43b5-8055-61d2a0695bfd/faktura_2015_10014.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10014",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500072"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500072/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500072/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500072",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508072"
                    }
                  },
                  "identifier": "8",
                  "downloadUrl": "http://localhost:8080/filer/986333b1-728e-4f89-a49e-e04c81de846a/faktura_2015_10008.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10008",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500075"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500075/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500075/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500075",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508075"
                    }
                  },
                  "identifier": "11",
                  "downloadUrl": "http://localhost:8080/filer/d59add8d-2ae3-4293-8719-999461550f40/faktura_2015_10011.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10011",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500083"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500083/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500083/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500083",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508083"
                    }
                  },
                  "identifier": "19",
                  "downloadUrl": "http://localhost:8080/filer/375cebe7-de26-40e9-8f7e-19f87d2ff9db/faktura_2015_10019.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10019",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500074"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500074/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500074/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500074",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508074"
                    }
                  },
                  "identifier": "10",
                  "downloadUrl": "http://localhost:8080/filer/d48d90a7-f051-4287-83f0-6155629746e7/faktura_2015_10010.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10010",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500076"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500076/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500076/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500076",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508076"
                    }
                  },
                  "identifier": "12",
                  "downloadUrl": "http://localhost:8080/filer/6423f3f1-20b0-4b44-a552-8db1aacb7b47/faktura_2015_10012.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10012",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500081"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500081/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500081/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500081",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508081"
                    }
                  },
                  "identifier": "17",
                  "downloadUrl": "http://localhost:8080/filer/2fe2e2f9-442c-4b89-981a-2503dcae701c/faktura_2015_10017.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10017",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500077"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500077/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500077/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500077",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508077"
                    }
                  },
                  "identifier": "13",
                  "downloadUrl": "http://localhost:8080/filer/568f368c-c9d3-4f34-bf35-2c28d34b116a/faktura_2015_10013.pdf",
                  "kind": "INVOICE"
                },
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508084"
                    }
                  },
                  "identifier": "20",
                  "downloadUrl": "http://localhost:8080/filer/768bfc4a-7c5a-4e73-9d40-09918e40c73d/faktura_2015_10013_purring.pdf",
                  "kind": "REMINDER"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10013",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              },
              {
                "description": "Purregebyr 13.04.2015",
                "netPrice": 6500,
                "vat": 0,
                "incomeAccount": "3900",
                "vatType": "NONE"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500079"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500079/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500079/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500079",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508079"
                    }
                  },
                  "identifier": "15",
                  "downloadUrl": "http://localhost:8080/filer/a49b9cb5-d33d-4336-a0f3-c03cb307b2ba/faktura_2015_10015.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10015",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500080"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500080/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500080/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500080",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508080"
                    }
                  },
                  "identifier": "16",
                  "downloadUrl": "http://localhost:8080/filer/6a32336d-9542-4b64-becd-96cf45f3566b/faktura_2015_10016.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10016",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500082"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500082/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6500082/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6500082",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6508082"
                    }
                  },
                  "identifier": "18",
                  "downloadUrl": "http://localhost:8080/filer/87b6cf52-8acf-402f-8ec4-39ca821b0bdf/faktura_2015_10018.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-01-01",
            "kind": "INVOICE",
            "identifier": "10018",
            "lines": [
              {
                "description": "my text",
                "netPrice": 10000,
                "vat": 2500,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6412070"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6412070/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6412070/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6412070",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/payments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/sales/6412070/payments/6417069"
                    }
                  },
                  "amount": 125,
                  "account": "1920:10001"
                }
              ]
            },
            "date": "2015-01-02",
            "kind": "EXTERNAL_INVOICE",
            "identifier": "abc123",
            "lines": [
              {
                "description": "Description",
                "netPrice": 100,
                "vat": 25,
                "incomeAccount": "3000",
                "vatType": "HIGH"
              }
            ],
            "customer": {
              "value": 6409069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6409069"
            }
          },
          {
            "_links": {
              "self": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6427069"
              },
              "https://fiken.no/api/v1/rel/payments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6427069/payments"
              },
              "https://fiken.no/api/v1/rel/attachments": {
                "href": "http://localhost:8080/api/v1/companies/test1/sales/6427069/attachments"
              },
              "alternate": {
                "href": "http://localhost:8080/foretak/test1/handel/#/salg/6427069",
                "type": "text/html"
              }
            },
            "_embedded": {
              "https://fiken.no/api/v1/rel/attachments": [
                {
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/api/v1/companies/test1/attachments/6434069"
                    }
                  },
                  "identifier": "1",
                  "downloadUrl": "http://localhost:8080/filer/42e46462-f58a-4f6b-8e4f-9b86232e02c0/faktura_2015_10001.pdf",
                  "kind": "INVOICE"
                }
              ]
            },
            "date": "2015-04-11",
            "kind": "INVOICE",
            "identifier": "10001",
            "lines": [
              {
                "description": "Maur",
                "netPrice": 1000,
                "vat": 0,
                "incomeAccount": "3010",
                "vatType": "NONE"
              }
            ],
            "customer": {
              "value": 6421069,
              "url": "http://localhost:8080/api/v1/companies/test1/contacts/6421069"
            }
          }
        ]
      }
    }
