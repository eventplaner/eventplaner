module.exports = (router) ->

  router.get '/user', (req, res) ->
    res.send 'hi'

  router.get '/user/:id', (req, res) ->
    res.send 'hi'

  router.post '/user/save', (req, res) ->
    res.send 'hi'

  router.get '/user/:id/friends', (req, res) ->
    res.send 'hi'

  router.get '/user/:user_id/friend/:id/add', (req, res) ->
    res.send 'hi'

  router.get '/user/:user_id/friend/:id/remove', (req, res) ->
    res.send 'hi'
