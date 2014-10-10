module.exports = (db, next) ->
  db.define 'user', {
    id: { type: 'number', key: true },
    name: String,
    email: String,
    telnumber: String
    passkey: String,
    active: Boolean
    createdate: Date,
    changedate: Date
  }

  db.define 'participant'
  next()
