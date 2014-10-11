module.exports = (db, next) ->
  db.define 'event', {
    id: { type: 'number', key: true },
    name: String,
    description: String
    start: Date,
    end: Date
    position_latitude: String,
    position_longitude: String
    active: Boolean
    createdate: Date,
    changedate: Date,
    createuser_id: Number
  }

  db.models.event.hasOne 'creator', db.models.person, { required: true }
  db.models.participant.hasOne 'event', db.models.event, { required: true }

  next()
