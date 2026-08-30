# Security Policy

## Two-dimensional security boundary

```text
                              SECURITY CONTROL
ASSET DIMENSION          EXCLUDE  VALIDATE  MOCK  LOG  PUBLISH
Real personal data         [X]      [X]      [ ]   [ ]    [ ]
Credentials and secrets    [X]      [X]      [ ]   [ ]    [ ]
Bureau/sanctions data      [X]      [X]      [X]   [ ]    [ ]
Build output               [X]      [X]      [ ]   [ ]    [ ]
Synthetic test data        [ ]      [X]      [X]   [X]    [X]

METAFIELDS
Data class : Synthetic portfolio data
Secrets    : Prohibited from Git
Build      : target/ excluded
Reporting  : Private disclosure to repository owner
Scope      : Source, configuration, documentation, Git history
Boundary   : No operational security assurance
```

Do not commit real customer or employee information, identity numbers, account numbers, bureau records, sanctions data, passwords, tokens, keys, production URLs, build output, or local test reports containing workstation paths.

Report suspected security concerns privately to the repository owner.