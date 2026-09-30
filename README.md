# fp-dokgen

[![Bygg og deploy](https://github.com/navikt/fp-dokgen/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/navikt/fp-dokgen/actions/workflows/build.yml)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=navikt_fp-dokgen&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=navikt_fp-dokgen)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=navikt_fp-dokgen&metric=alert_status)](https://sonarcloud.io/dashboard?id=navikt_fp-dokgen)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=navikt_fp-dokgen&metric=bugs)](https://sonarcloud.io/dashboard?id=navikt_fp-dokgen)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=navikt_fp-dokgen&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=navikt_fp-dokgen)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=navikt_fp-dokgen&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=navikt_fp-dokgen)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=navikt_fp-dokgen&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=navikt_fp-dokgen)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=navikt_fp-dokgen&metric=sqale_index)](https://sonarcloud.io/dashboard?id=navikt_fp-dokgen)


Maler og innhold for generering av brev for foreldrepenger, svangerskapspenger og engangsstønad

# Komme i gang
....

# Bruker
https://github.com/navikt/dokgen

## Innføring av felles uttaksplan
Deploy støtte for `uttaksplan.perioder` i fp-dokgen før fp-soknad sender den nye
uttaksplanen uendret til kvitteringsgenerering. Førstegangs- og endringssøknader
viser søkerens perioder direkte fra den nye modellen. Bare manglende eller
`null` `perioder` bruker gammel `uttaksperioder`; en tom liste bruker ikke gammel plan.
Endringssøknader viser de innsendte søkerperiodene uten filtrering på dato eller vedtatt resultat.

# Henvendelser
Spørsmål knyttet til koden eller prosjektet kan gjøres ved bruk av Issue her på GitHub.

## For Nav-ansatte
Interne henvendelser kan stilles på Slack-kanalen #teamforeldrepenger
