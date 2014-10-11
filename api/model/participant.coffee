module.exports = (db, next) ->
  db.define 'participant', {
    id: { type: 'number', key: true },
    status: { type: "boolean", defaultValue: undefined }
  }

  db.models.participant.hasOne 'user', db.models.user, {
    reverse: 'participant',
    autoFetch: true,
    required: true
  }

  db.models.participant.hasOne 'event', db.models.event, {
    reverse: 'participant',
    autoFetch: true,
    required: true
  }

  next()
