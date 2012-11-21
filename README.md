# koko

koko is a highly specialized web form for the ASUC/GA with the purpose of streamlining the flow of creating, approving, and
reviewing funding requests.

## Naming

The officially sanctioned description (from design documents) is "Graduate Assembly/ASUC Funding Request Application System".
GA/ASUCFRAS was not very pretty, so it was arbitrarily decided to name the project after the arms dealer Koko Hekmatyar
from Jormungand (because it handles money, get it?).

## Installation

Theoretically, Maven makes installation, packaging, and deployment super easy. In practice, this is not the case.

I can't say much, but at the moment, you need Tomcat 7, and then you put the .war file somewhere. That's it.

## Configuration

Eventually, there will be an easy way to configure parameters such as URL and DBMS credentials, but right now they're hardcoded
into the XML-based Spring configuration in WEB-INF/. Most likely we'll use some form of .properties configuration, so that
will eventually become Tomcat's problem.

## FAQ

Prepared more for potential team members rather than clients.

### Why Java?

Because a certain group member is masochistic.

### Why Spring Framework?

Because the requiements documents told us to.

### How do I into Spring Framework?

I have no idea.