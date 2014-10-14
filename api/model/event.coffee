module.exports = (db, next) ->
  db.define 'event', {
    id: { type: 'number', key: true },
    name: String,
    description: String,
    start: Date,
    end: Date,
    position_latitude: String,
    position_longitude: String,
    createdate: Date,
    changedate: Date,
    createuser_id: Number
  }

  next()
