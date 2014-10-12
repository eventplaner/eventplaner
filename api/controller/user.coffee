module.exports = (router) ->

  # Gets all users
  # @returns all users
  router.get '/user', (req, res) ->
    req.models.user.find {}, (err, user) ->
      if err then rest.status(500).send err
      res.json user

  # Gets a user by its id
  # @id {Number} The id of the user
  router.get '/user/:id', (req, res) ->
    req.models.user.get req.params.id, (err, user) ->
      if err then rest.status(500).send err
      res.json user

  # Saves (creates or updates) the data of a User
  # @returns The created or updated user
  router.post '/user/save', (req, res) ->
    reqUser = {
      name: req.body.name,
      email: req.body.email,
      telnumber: req.body.telnumber,
      active: req.body.active,
      createdate: new Date(),
      changedate: new Date()
    }
    if req.body.id > 0
      req.models.user.get req.body.id, (err, user) ->
        if err then rest.status(500).send err
        user.save reqUser, (err, savedUser) ->
          if err then rest.status(500).send err
          res.json savedUser
    else
      req.models.user.create reqUser, (err, user) ->
        if err then rest.status(500).send err
        res.json user

  router.get '/user/:id/friends', (req, res) ->
    res.send 'todo ...'

  router.get '/user/:user_id/friend/:id/add', (req, res) ->
    res.send 'todo ...'

  router.get '/user/:user_id/friend/:id/remove', (req, res) ->
    res.send 'todo ...'
