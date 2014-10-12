module.exports = (db, next) ->
  db.define 'participant', {
    id: { type: 'number', key: true },
    status: { type: "boolean", defaultValue: null }
  }, { cache: false }

  db.models.participant.hasOne 'user', db.models.user, {
    reverse: 'participant',
    autoFetch: true,
    required: true,
    cache: false
  }

  db.models.participant.hasOne 'event', db.models.event, {
    reverse: 'participant',
    autoFetch: true,
    required: true,
    cache: false
  }

  next()
