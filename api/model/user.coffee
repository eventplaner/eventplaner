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

  db.define 'participant', {
    id: { type: 'number', key: true },
    status: Boolean
  }

  db.models.participant.hasOne 'user', db.models.user, { required: true }

  next()
