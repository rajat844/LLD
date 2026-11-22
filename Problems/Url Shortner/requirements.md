# URL Shortner

## Functional Requirements

- User can submit a long url to generate unique short code. The System must validate the URL format before shortening and reject malformed URLs.
- If a user submits a long URL they previously shortened, the system must return the existing short code only if mapping is still active. Otherwise it generates a new shortcode.
- Given a valid, active shortcode, the system resolves and returns the original long URL. If the short code doesn't exist, has been deleted or is expired, the system must fail explicitly.
- Once generated a short code mapping cannot be edited, to edit it user can delete the existing mapping and create a new one.
- Every short code will be available for 10 days, visiting link will not reset the TTL. Expired links are detected and deleted lazily on read.
- User can view all the active short code and delete any url they own.
- User can select short code generation strategy when creating a link. System will deafaults to random is none is specified.

## Non Functional Requirements

- All writes will be thread safe and reads will be non-blocking.
- The System will handle the collision itself.
