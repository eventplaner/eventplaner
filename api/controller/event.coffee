module.exports = (router) ->

  # Gets a List of all events
  # @returns All events
  router.get '/event', (req, res) ->
    req.models.event.find {}, (err, events) ->
      if err then rest.status(500).send err
      res.json events

  # Saves (creates or updates) a event
  # @returns the created/updated event
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
        if err then rest.status(500).send err
        event.save reqEvent, (err, savedEvent) ->
          if err then rest.status(500).send err
          res.json savedEvent
    else
      req.models.event.create reqEvent, (err, event) ->
        if err then rest.status(500).send err
        res.json event

  # Gets one event by its id
  # @id {Number} The event id
  # @returns The found event
  router.get '/event/:id', (req, res) ->
    req.models.event.get req.params.id, (err, event) ->
      if err then rest.status(500).send err
      res.json event

  # Gets all participants of an event
  # @event_id {Number} The Id of the event
  # @returns All participants of this Event
  router.get '/event/:event_id/participants', (req, res) ->
    req.models.event.get req.params.event_id, (err, event) ->
      if err then rest.status(500).send err
      if event?
        event.getParticipant (err, participants) ->
          if err then rest.status(500).send err
          res.json participants
      else
        res.status(204).json { id: req.params.event_id, message: "No data" }

  # Adds a new participant to an event
  # @event_id {Number} The Id of the event
  # @id {Number} The id of the User who participates on the event
  # @returns The new participant
  router.get '/event/:event_id/participant/:id/add', (req, res) ->
    req.models.participant.create {
      event_id: req.params.event_id,
      user_id: req.params.id,
      status: undefined
    }, (err, participant) ->
      if err then rest.status(500).send err
      res.json participant

  # Removes a participant from an event
  # @event_id {Number} The Id of the event
  # @id {Number} The id of the User who no longer participates on the event
  router.get '/event/:event_id/participant/:id/remove', (req, res) ->
    req.models.participant.find({
      event_id: req.params.event_id,
      user_id: req.params.id
    }).remove (err) ->
      if err then rest.status(500).send err
      res.json {message: 'removed'}

  # Set the status of the participation
  # @event_id {Number} The Ide of the event
  # @id {Number} The Id of the User who participates on the event
  # @value {Boolean} Wether the User wants to participate on a event
  router.get '/event/:event_id/participant/:id/participate/:value', (req, res) ->
    participants = []
    req.models.participant.find({
      event_id: req.params.event_id,
      user_id: req.params.id
    }).each (participant) ->
      participant.status = (req.param.value is true)
      participants.push participant
    .save (err, participant) ->
      if err then rest.status(500).send err
    res.json participants
