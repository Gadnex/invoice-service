# invoice-service
An invoice service developed as an example for the EOH Digital Platoon Java Developer Assignment

## Add Invoice
POST http://localhost:8080/invoices

Header: Content-Type: application/json

Request Body:
```JSON
{
	"client": "Willy",
	"vatRate": 15,
	"lineItems": [
		{
			"quantity": 10,
			"description": "Widget",
			"unitPrice": 1.05
		}
	]
}
```

Response Body:
```JSON
{
    "id": 1,
    "client": "Willy",
    "vatRate": 15,
    "invoiceDate": "2018-06-20T15:13:15.902+0000",
    "lineItems": [
        {
            "id": 1,
            "quantity": 10,
            "description": "Widget",
            "unitPrice": 1.05,
            "lineItemTotal": 10.5
        }
    ],
    "subTotal": 10.5,
    "vat": 1.58,
    "total": 12.08
}
```

## View All Invoice
GET http://localhost:8080/invoices

Response Body:
```JSON
[
    {
        "id": 1,
        "client": "Willy",
        "vatRate": 15,
        "invoiceDate": "2018-06-20",
        "lineItems": [
            {
                "id": 1,
                "quantity": 10,
                "description": "Widget",
                "unitPrice": 1.05,
                "lineItemTotal": 10.5
            }
        ],
        "subTotal": 10.5,
        "vat": 1.58,
        "total": 12.08
    }
]
```

## View Invoice
GET http://localhost:8080/invoices/1

Response Body:
```JSON
{
    "id": 1,
    "client": "Willy",
    "vatRate": 15,
    "invoiceDate": "2018-06-20",
    "lineItems": [
        {
            "id": 1,
            "quantity": 10,
            "description": "Widget",
            "unitPrice": 1.05,
            "lineItemTotal": 10.5
        }
    ],
    "subTotal": 10.5,
    "vat": 1.58,
    "total": 12.08
}
```
