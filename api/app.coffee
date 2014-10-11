express = require 'express'
orm = require 'orm'
app = express()
router = express.Router()

app.use orm.express "mysql://root:root@localhost/eventplaning", {
  define: (db, models, next) ->
    db.load './model/user', (err) ->
      if err
        throw err
      models.user = db.models.user
      models.event = db.models.event
      models.participant = db.models.participant
    next()
  }

(require './controller/event')(router)
(require './controller/user')(router)

app.use '/api', router

app.listen 4000
