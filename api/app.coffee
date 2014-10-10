express = require 'express'
orm = require 'orm'
app = express()

app.use orm.express "mysql://root:root@localhost/eventplaning", {
  define: (db, models, next) ->
    db.load './model/user', (err) ->
      if err
        throw err
      models.user = db.models.user
    next()
  }

app.listen 4000

app.get '/', (req, res) ->
  res.send "hi"
