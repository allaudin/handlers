# handlers
Source project for article https://allaudin.github.io/looper-handler-api/


## Ease Volley Wrapper

**Ease** is a wrapper around [Volley](https://github.com/google/volley) for handling network 
responses more effectively. It offers **auto parsing** of response to **any specific** type with **clean**
and **intuitive** API for creating requests and handling responses.

By default, _Ease_ gives support for handling following kind of responses but configuring it 
for **any other** response is tremendously easy, it is just a matter updating a single Class (Response Type). 

    {
 	    "result_description": "des",
 	    "data": {}
    }
                 
    {
  	    "result_description": "des",
  	    "data": [{}]
    }

## Why should I use Ease?

[Volley](https://github.com/google/volley) offers a simple API for handling network calls and responses, 
but for relatively bigger projects it is cumbersome to stick with default Volley API for handling 
network calls.

1. While Volley's toolbox supports many Request formats out of the box, it does not allow us to parse response 
to a **specific or desired type**. 

2. Volley's default response listener does not allow to handle multiple request with same callback handler.

3. When there is more than a single request on an Activity or Fragment, it pollutes code with anonymous 
handlers making it hard to read and maintain.

4. It does not support (by default) progress dialogs, you have to write your own for showing it before a 
network call.


> **Ease** is designed with having all the above issues in mind. It does most of the work on behalf of 
> developers, leaving behind the **minimal** work for them.



## Building a Request

  1. Creating Request Headers *[Optional]*
  
      ```java
      RequestHeaders headers = RequestHeaders.newInstance().put("some", "value").put("key", "va");
      ```
  2. Creating Request Body *[Optional]*   
  
      ```java
       RequestBody body = RequestBody.newInstance().put("body", "value").put("other", "val");
      ```
  
  3. Creating Request
  
  
      ```java
        EaseRequest.type(new TypeToken<EaseResponse<List<UserModel>>>(){}) // type token discussed below
                        .method().post() // request type
                        .body(body) // body created in step 2
                        .headers(headers) // headers created in step 1
                        .requestId(100) // request ID for this request
                        .endPoint("users") 
                        .build().execute(this); // this = context
      ```

## Creating type tokens

In order to parse a network to arbitrary objects using *Gson* you need to pass `type information` of the `Object`
to `Ease`. This can be done with `TypeToken` class which accepts required type as parametrized type. e.g.

    new TypeToken<EaseResponse<List<UserModel>>>(){}

It will convert the *data* key from network response into a list of `UserModels`.

This syntax is a little ugly but we have no other choice in case of *Generic types*. For creating token of 
**non-generic** types, `Ease` has a utility class for this purpose with easy to remember syntax. e.g. following 
line of code returns a `TypeToken` for type `UserModel`

    EaseType.get(UserModel.class)



Made with :heart: by allaudin
