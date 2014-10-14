express = require 'express'
bodyParser = require 'body-parser'
orm = require 'orm'
app = express()
router = express.Router()

app.use bodyParser.urlencoded({ extended: false })

app.use orm.express "mysql://root:root@localhost/eventplaning", {
  define: (db, models, next) ->

    db.settings.set('instance.cache', false)

    db.load './model/user', (err) ->
      if err then throw err
      models.user = db.models.user
      models.participant = db.models.participant

    db.load './model/event', (err) ->
      if err then throw err
      models.event = db.models.event

    db.load './model/participant', (err) ->
      if err then throw err
      models.participant = db.models.participant

    next()
  }

(require './controller/event')(router)
(require './controller/user')(router)

app.use '/api', router

app.listen 4000

#process.on 'uncaughtException', (err) ->
#  console.log err
