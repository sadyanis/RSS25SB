<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:media="http://search.yahoo.com/mrss/"
                xmlns:p="http://univrouen.fr/rss25"
                xmlns:atom="http://www.w3.org/2005/Atom"
                version="1.0">

  <xsl:output method="xml" encoding="UTF-8" indent="yes"/>
  
  <xsl:template match="/rss/channel">
    <p:feed xmlns:p="http://univrouen.fr/rss25"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://univrouen.fr/rss25 rss25.tp1.xsd"
            lang="fr" ver="25">
      
      <p:title><xsl:value-of select="title"/></p:title>
      <p:pubDate>
        <xsl:value-of select="translate(pubDate, ',', '')"/>
      </p:pubDate>
      <p:copyright><xsl:value-of select="copyright"/></p:copyright>
      <p:link rel="self" type="application/atom+xml">
        <xsl:attribute name="href">
          <xsl:value-of select="link"/>
        </xsl:attribute>
      </p:link>

      <xsl:for-each select="item">
        <p:item>
          <p:guid><xsl:value-of select="guid"/></p:guid>
          <p:title><xsl:value-of select="title"/></p:title>
          <p:category term="Culture"/>
          <p:published>
            <xsl:value-of select="pubDate"/>
          </p:published>

          <xsl:if test="media:content">
            <p:image type="jpeg">
              <xsl:attribute name="href">
                <xsl:value-of select="media:content/@url"/>
              </xsl:attribute>
              <xsl:attribute name="alt">Image de l'article</xsl:attribute>
            </p:image>
          </xsl:if>

          <p:content type="text"><xsl:value-of select="description"/></p:content>

          <p:author>
            <p:name>Le Monde</p:name>
          </p:author>
        </p:item>
      </xsl:for-each>
    </p:feed>
  </xsl:template>

</xsl:stylesheet>
