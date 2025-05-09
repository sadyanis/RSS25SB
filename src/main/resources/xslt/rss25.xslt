<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:p="http://univ.fr/rss25">
    <xsl:output method="html" encoding="UTF-8"/>

    <xsl:template match="/">
        <html>
         
            <head>
            	<link rel="stylesheet" href="./style.css"/>
                <title>TP3 - Flux RSS25</title>
            </head>
            <body>
                <xsl:call-template name="titrePrincipal"/>
                <xsl:call-template name="description"/>
                <xsl:call-template name="sommaire" />
                <xsl:call-template name="detailsArticles"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template name="titrePrincipal">
        <h1>TP3 - Flux RSS25</h1>
        <p>Le 5 mars 2025</p>
    </xsl:template>

    <xsl:template name="description">
        <h2>Description</h2>
        <ul>
            <li>Contenu: <xsl:value-of select="p:feed/p:title"/></li>
            <li>Publié le : 
                <xsl:call-template name="formatDate">
                    <xsl:with-param name="dateISO" select="p:feed/p:pubDate"/>
                </xsl:call-template>
            </li>
            <li>Copyright : <xsl:value-of select="p:feed/p:copyright"/></li>
        </ul>
    </xsl:template>
    <xsl:template name="sommaire">
    <h2>Sommaire</h2>
    <ol>
    	<xsl:for-each select="/p:feed/p:item">
    	
    		<li><xsl:value-of select="p:title"/></li>
    	
    	</xsl:for-each>
    </ol>
    <p><xsl:value-of select="count(/p:feed/p:item)"/> Articles </p>
    </xsl:template>

    <xsl:template name="detailsArticles">
        <section>
            <h2>Détails des informations</h2>
            <xsl:for-each select="/p:feed/p:item">
                <div>
                    <h3><xsl:value-of select="p:title"/></h3>
                    <p><i>(guid= <xsl:value-of select="p:guid"/>)</i></p>
                    
                    <xsl:call-template name="afficherImage"/>
                    <xsl:call-template name="afficherCategories"/>
                    <xsl:call-template name="afficherDate"/>
                    <xsl:call-template name="afficherContenu"/>
                    <xsl:call-template name="afficherAuteurs"/>
                </div>
            </xsl:for-each>
        </section>
    </xsl:template>

    <xsl:template name="afficherImage">
        <xsl:if test="p:image">
            <div>
                <img>
                    <xsl:attribute name="src">
                        <xsl:value-of select="p:image/@href"/>
                    </xsl:attribute>
                    <xsl:attribute name="alt">
                        <xsl:value-of select="p:image/@alt"/>
                    </xsl:attribute>
                    <xsl:attribute name="width">500</xsl:attribute>
                    <xsl:attribute name="height">300</xsl:attribute>
                </img>
            </div>
        </xsl:if>
    </xsl:template>

    <xsl:template name="afficherCategories">
        <div>
            <p>Catégories :  
                <xsl:for-each select="p:category">
                    <xsl:value-of select="@term"/>
                    <xsl:if test="position() != last()">, </xsl:if>
                </xsl:for-each>  
            </p>
        </div>
    </xsl:template>

    <xsl:template name="afficherDate">
        <div>
            <xsl:choose>
                <xsl:when test="p:published">
                    Publié le : 
                    <xsl:call-template name="formatDate">
                        <xsl:with-param name="dateISO" select="p:published"/>
                    </xsl:call-template>
                </xsl:when>
                <xsl:when test="p:updated">
                    Mise à jour le : 
                    <xsl:call-template name="formatDate">
                        <xsl:with-param name="dateISO" select="p:updated"/>
                    </xsl:call-template>
                </xsl:when>
            </xsl:choose>
        </div>
    </xsl:template>

    <xsl:template name="afficherContenu">
        <div>
            <xsl:choose>
                <xsl:when test="p:content/@type='html'">
                    <xsl:value-of select="p:content" disable-output-escaping="yes"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="p:content"/>
                </xsl:otherwise>
            </xsl:choose>
        </div>
    </xsl:template>

    <xsl:template name="afficherAuteurs">
        <div>
            <xsl:choose>
                <xsl:when test="p:author">
                    <xsl:for-each select="p:author">
                        <div>
                            <p>Auteur: 
                                <xsl:value-of select="p:name"/>
                                <xsl:if test="p:email"> (<xsl:value-of select="p:email"/>)</xsl:if>
                            </p>
                        </div>
                    </xsl:for-each>
                </xsl:when>
                <xsl:when test="p:contributor">
                    <xsl:for-each select="p:contributor">
                        <div>
                            <p>Contributeur: 
                                <xsl:value-of select="p:name"/>
                                <xsl:if test="p:email"> (<xsl:value-of select="p:email"/>)</xsl:if>
                            </p>
                        </div>
                    </xsl:for-each>
                </xsl:when>
            </xsl:choose>
        </div>
    </xsl:template>

    
    <xsl:template name="formatDate">
        <xsl:param name="dateISO"/>
        
        <!-- Extraction des valeurs -->
        <xsl:variable name="jour" select="substring($dateISO, 9, 2)"/>
        <xsl:variable name="mois" select="substring($dateISO, 6, 2)"/>
        <xsl:variable name="annee" select="substring($dateISO, 1, 4)"/>
        
        
        <xsl:value-of select="number($jour)"/> 
        
        
        <xsl:choose>
            <xsl:when test="$mois='01'"> Janv</xsl:when>
            <xsl:when test="$mois='02'"> Févr</xsl:when>
            <xsl:when test="$mois='03'"> Mars</xsl:when>
            <xsl:when test="$mois='04'"> Avril</xsl:when>
            <xsl:when test="$mois='05'"> Mai</xsl:when>
            <xsl:when test="$mois='06'"> Juin</xsl:when>
            <xsl:when test="$mois='07'"> Juil</xsl:when>
            <xsl:when test="$mois='08'"> Août</xsl:when>
            <xsl:when test="$mois='09'"> Sept</xsl:when>
            <xsl:when test="$mois='10'"> Oct</xsl:when>
            <xsl:when test="$mois='11'"> Nov</xsl:when>
            <xsl:when test="$mois='12'"> Déc</xsl:when>
        </xsl:choose> 
        
      
        <xsl:text> </xsl:text>
        <xsl:value-of select="$annee"/>
    </xsl:template>

</xsl:stylesheet>