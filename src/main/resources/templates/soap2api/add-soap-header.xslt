<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:soapenv="http://www.w3.org/2003/05/soap-envelope">

  <xsl:output encoding="utf-8" indent="yes" method="xml" omit-xml-declaration="yes" />
  <xsl:param name="username" />
  <xsl:param name="password" />
  <xsl:param name="messageId" />
  <xsl:param name="timestamp" />


  <xsl:template match="/">
    <xsl:apply-templates select="node()|@*" />
  </xsl:template>


  <xsl:template match="*[local-name()='Header']">
      
     <soapenv:Header xmlns:soap="http://www.w3.org/2003/05/soap-envelope" 
            xmlns:wsa="http://www.w3.org/2005/08/addressing"
            xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" >

        <wsa:MessageID><xsl:value-of select="$messageId"/></wsa:MessageID>
        <wsse:TimeStamp xmlns:ns1="http://htng.org/1.3/Header/"><xsl:value-of select="$timestamp"/></wsse:TimeStamp>
        <wsse:Security>
            <wsse:UsernameToken>
                <wsse:Username><xsl:value-of select="$username"/></wsse:Username>
                <wsse:Password><xsl:value-of select="$password"/></wsse:Password>
            </wsse:UsernameToken>
        </wsse:Security>
    </soapenv:Header>
    
  </xsl:template>


  <xsl:template match="node()|@*">
    <xsl:copy>
      <xsl:apply-templates select="node()|@*" />
    </xsl:copy>
  </xsl:template>
  
</xsl:stylesheet>