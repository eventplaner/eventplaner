module.exports = (router) ->

  router.get '/event', (req, res) ->
    res.send 'hi'

  router.post '/event/save', (req, res) ->
    res.send 'hi'

  router.get '/event/:id', (req, res) ->
    res.send 'hi'

  router.get '/event/:event_id/participants', (req, res) ->
    res.send 'hi'

  router.get '/event/:event_id/participant/:id/add', (req, res) ->
    res.send 'hi'

  router.get '/event/:event_id/participant/:id/remove', (req, res) ->
    res.send 'hi'

  router.get '/event/:event_id/participant/:id/participate/true', (req, res) ->
    res.send 'hi'

  router.get '/event/:event_id/participant/:id/participate/false', (req, res) ->
    res.send 'hi'
