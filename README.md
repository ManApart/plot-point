# Plot Point

## Design Goals

text editor
all text is version controlled
text is grouped in topics
any topic mentioned anywhere is a link to that topic
any topic can provide all references to that topic
multiple windows can be moved around and contain different documents

## Implementation Details
Write topics as json to file system

Topic

* type
* name (must be unique)
* text

relationships generated at runtime

## UI
Browse all topics, sorted by type and then name
Click a topic to open its display
References are underlined and clickable