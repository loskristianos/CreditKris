# Class final project - banking application

## Project Stages (initial plan)

- Stage 1 - CLI only, csv (or similar) data storage
- Stage 2 - CLI only, database data storage
- Stage 3 - GUI and CLI, database data storage[^1]
- Stage 4 - password hashes/encryption/security/etc.
- Stage 5 - refactor and move text strings etc. to a resources folder for future localization/easier changes to wording[^2]

[^1]: Stages 2 & 3 can be swapped if necessary (i.e. develop a GUI using csv storage first then implement database storage after both UIs are done).
[^2]: This should probably (ideally)[^3] be done between v2 and v3 because the GUI will use a lot of the same text strings as the CLI, so it makes sense to have them both refer to the same string rather than duplicate hard-coded text. 
[^3]: *Ideally* ideally it should be done like this from the start.