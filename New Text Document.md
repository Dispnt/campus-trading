## User Login / Register

### register
- request

```
{
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: {"phoneNumber": "17521638486","name": "虞佳健", "stuId":"18011433","password": "121231", "intro":"你好","userName":"YuYu"}
    url: '/user/register'
}
```

- response

```
{
    "id": 2,
    "jsonWebToken": "8390",
    "stuId": 18011432,
    "password": "123321",
    "intro": "你好",
    "userName": "YuYu",
    "purchaseHistory": null,
    "items": null
}
```

### login
- request

```
{
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: {"stuId":"18011433","password":"121231"}
    url: '/user/authenticate'
}
```

- response

```
{
    {
    "id": 1,
    "jsonWebToken": <user_token>,
    "stuId": 18011433,
    "password": "121231",
    "intro": "你好",
    "purchaseHistory": [],
    "items": []
}
}
```

## Items
### get item list

- request
```
{
    method: 'GET',
    url: '/item/list'
}
```

- response

```
[
        [
    {
        "id": 1,
        "name": "Apple",
        "price": 4000,
        "imgUrl": "https://...",
        "productComment": "fruit",
        "sellerId": 1,
        "purchaseHistory": []
    },
    {
        "id": 2,
        "name": "Banana",
        "price": 3154,
        "imgUrl": "https://...",
        "productComment": "fruitt",
        "sellerId": 1,
        "purchaseHistory": []
    },
    {
        "id": 3,
        "name": "kiwi",
        "price": 4396,
        "imgUrl": "https://....",
        "productComment": "fruittt",
        "sellerId": 1,
        "purchaseHistory": []
    }
]
]
```
### add item

- request

```
{
    method: 'POST',
    headers: { 'Authorization' : '<user_token>', 'Content-Type': 'application/json' },
    url: '/user/add'
    body: {"name":"test","price":"123","imgUrl":"https://...","productComment":"a test"}
}
```

- response

```
 {
    "id": 123,
    "name": "test",
    "price": 123,
    "imgUrl": "https://...",
    "productComment": "a test",
    "sellerId": 1,
    "purchaseHistory": []
}
```

### buy

- request

```
{
    method: 'POST',
    headers: { 'Authorization' : '<user_token>', 'Content-Type': 'application/json' },
    url: '/user/buy'
    body: {"itemId":1}
}
```

- response

```
{
    "id": 1,
    "jsonWebToken": "5220",
    "stuId": 18011433,
    "password": "123321",
    "intro": "你好",
    "userName": "Rowan",
    "purchaseHistory": [
        {
            "id": 2,
            "userId": 1,
            "itemId": 1
        },
        {
            "id": 4,
            "userId": 1,
            "itemId": 1
        }
    ],
    "items": [
        {
            "id": 1,
            "name": "Apple",
            "price": 4000,
            "imgUrl": "https://...",
            "productComment": "fruit",
            "sellerId": 1,
            "purchaseHistory": [
                {
                    "id": 2,
                    "userId": 1,
                    "itemId": 1
                },
                {
                    "id": 4,
                    "userId": 1,
                    "itemId": 1
                }
            ]
        },
        {
            "id": 2,
            "name": "Banana",
            "price": 3154,
            "imgUrl": "https:...",
            "productComment": "fruitt",
            "sellerId": 1,
            "purchaseHistory": []
        }
    ]
}
```

## get purchase history

- request
```
{
    method: 'GET',
    headers: { 'Authorization' : '<user_token>'},
    url: '/user/purchase/history'
}
```

- response
```
[
        [
    {
        "id": 1,
        "name": "Apple",
        "price": 4000,
        "imgUrl": "https://...",
        "productComment": "fruit",
        "sellerId": 1,
        "purchaseHistory": [
            {
                "id": 2,
                "userId": 1,
                "itemId": 1
            },
            {
                "id": 4,
                "userId": 1,
                "itemId": 1
            },
            {
                "id": 5,
                "userId": 1,
                "itemId": 1
            }
        ]
    },
    {
        "id": 1,
        "name": "Apple",
        "price": 4000,
        "imgUrl": "https://...",
        "productComment": "fruit",
        "sellerId": 1,
        "purchaseHistory": [
            {
                "id": 2,
                "userId": 1,
                "itemId": 1
            },
            {
                "id": 4,
                "userId": 1,
                "itemId": 1
            },
            {
                "id": 5,
                "userId": 1,
                "itemId": 1
            }
        ]
    }
]
]
```