# assignment1 #

## Build & Run ##

```sh
$ cd assignment1
$ sbt
> jetty:start
```

* POST /messages/:id -- Create new message if it does't exist with the id, else 501
* PUT /messages/:id -- Rewrite existing message, else 501
* DELETE /messages/:id -- Delete message by id if it exist, else 501
* GET /messages/:id -- Return message by id if it exist, else 404
* GET /messages -- Return array of all existing messages with theirs id's else empty array

Messages are serialized into ```case class Message(text: String)``` and stored in memory in ```HashMap[Int, Message]```, where key is ```Id``` of Message