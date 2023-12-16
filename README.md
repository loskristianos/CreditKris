# Class final project - banking application

## Project Stages (initial plan)

- v1 - CLI only, csv (or similar) data storage
- v2 - CLI only, database data storage [^1]
- v3 - GUI and CLI, database data storage1
- v4 - password hashes/encryption/security/etc.
- v5 - refactor and move text strings etc. to a resources folder for future localization/easier changes to wording[^2]

[^1] v1 and v2 can be swapped if necessary (i.e. develop a GUI using csv storage first then implement database storage after both UIs are done).
[^2] this should probably (ideally)[^3] be done between v2 and v3 because the GUI will use a lot of the same text strings as the CLI, so it makes sense to have them both refer to the same string rather than duplicate hard-coded text. 
[^3] *ideally* ideally it should be done like this from the start.