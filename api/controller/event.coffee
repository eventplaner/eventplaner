module.exports = (router) ->

  router.get '/event', (req, res) ->
    req.models.event.find {}, (err, events) ->
      if err then res.send err
      res.json events

  router.post '/event/save', (req, res) ->

    reqEvent = {
      name: req.body.name,
      description: req.body.description,
      start: req.body.start,
      end: req.body.end,
      position_latitude: req.body.position_latitude,
      position_longitude: req.body.position_longitude,
      createdate: new Date(),
      changedate: new Date(),
      createuser_id: req.body.createuser_id
    }

    if req.body.id > 0
      req.models.event.get req.body.id, (err, event) ->
        if err then res.send err
        event.save reqEvent, (err, savedEvent) ->
          if err then res.send err
          res.json savedEvent
    else
      req.models.event.create reqEvent, (err, event) ->
        if err then res.send err
        res.json event

  router.get '/event/:id', (req, res) ->
    req.models.event.get req.params.id, (err, event) ->
      if err then res.send err
      res.json event

  router.get '/event/:event_id/participants', (req, res) ->
    req.models.event.get req.params.event_id, (err, event) ->
      if err then res.send err
      event.getParticipant (err, participants) ->
        if err then res.send err
        res.json participants

  router.get '/event/:event_id/participant/:id/add', (req, res) ->
    req.models.participant.create {
      event_id: req.params.event_id,
      user_id: req.params.id
    }, (err, participant) ->
      if err then res.send err
      res.json participant

  router.get '/event/:event_id/participant/:id/remove', (req, res) ->
    req.models.participant.find({
      event_id: req.params.event_id,
      user_id: req.params.id
    }).remove (err) ->
      if err then res.send err
      res.json {message: 'removed'}


  router.get '/event/:event_id/participant/:id/participate/true', (req, res) ->
    res.send 'hi'

  router.get '/event/:event_id/participant/:id/participate/false', (req, res) ->
    res.send 'hi'
