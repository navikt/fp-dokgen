# fp-dokgen

Template and styling repo for letters and receipts to citizens and employers.

## Shared context

- Source of truth for shared domain, architecture, and conventions: `navikt/fp-context`
- Copilot Space: `navikt/TeamForeldrepenger`

## Repo-specific context

| Topic             | Details                                                                                     |
|-------------------|---------------------------------------------------------------------------------------------|
| Role              | Owns letter templates and styling for foreldrepenger, svangerskapspenger, and engangsstonad |
| Consumers         | `fp-formidling`, `fp-soknad`, `fp-inntektsmelding`                                          |
| Tech stack        | Standard fp Java backend; stateless; no authentication                                      |
| Main integrations | none                                                                                        |
| Data              | stateless, no DB |

Accepts requests from pre-authorized applications (naiserator); no further authentication or authorization.

## Entry points

`DokumentGeneratorRestTjeneste` accepts requests with template name, language selection, and input data for merging into the template.
- genererPdfDokument: returns PDFA from template and input
- genererHtmlDokument: returns HTML from template and input

## Verification

- Verify changes through consuming flows, typically in `fp-formidling`, `fp-soknad` or `fp-inntektsmelding`.
- `fp-autotest` does not target this repo directly, only indirectly through consumers.
