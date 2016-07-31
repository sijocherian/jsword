<?xml version="1.0"?>

<xsl:stylesheet	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

  <xsl:output method="text"/>

  <xsl:template match="/">
import java.io.File;

/**
 * This is autogenerated code created by config-test.xsl stylesheet run against config.xml.
 * It should generally be altered by editing config.xml or (less often) by editing
 * config-test.xsl.
 * <p>It is stored in SVN purely on the principle that users ought to be able to compile
 * without needed all the code-generation tools.</p>
 * see ../../etc/config-test.xsl
 * see ../../resource/config.xml
 * @author Joe Walker
 */
public class ConfigTest
{
    public static void main(String[] args) throws Exception
    {
        String stemp = ""; //$NON-NLS-1$
        String[] astemp = new String[0];
        File[] aftemp = new File[0];
        File ftemp = new File(""); //$NON-NLS-1$
        boolean btemp = false;
        Class ctemp = null;
        int itemp = 0;
        <xsl:apply-templates select="/config/option/introspect"/>
    }
}
  </xsl:template>

  <xsl:template match="/config/option/introspect">
        stemp = <xsl:value-of select="@class"/>.get<xsl:value-of select="@property"/>();
        <xsl:value-of select="@class"/>.set<xsl:value-of select="@property"/>(stemp);
  </xsl:template>

  <xsl:template match="/config/option[@type='boolean']/introspect">
        btemp = <xsl:value-of select="@class"/>.is<xsl:value-of select="@property"/>();
        <xsl:value-of select="@class"/>.set<xsl:value-of select="@property"/>(btemp);
  </xsl:template>

  <xsl:template match="/config/option[@type='path']/introspect">
        aftemp = <xsl:value-of select="@class"/>.get<xsl:value-of select="@property"/>();
        <xsl:value-of select="@class"/>.set<xsl:value-of select="@property"/>(aftemp);
  </xsl:template>

  <xsl:template match="/config/option[@type='file']/introspect">
        ftemp = <xsl:value-of select="@class"/>.get<xsl:value-of select="@property"/>();
        <xsl:value-of select="@class"/>.set<xsl:value-of select="@property"/>(ftemp);
  </xsl:template>

  <xsl:template match="/config/option[@type='directory']/introspect">
        ftemp = <xsl:value-of select="@class"/>.get<xsl:value-of select="@property"/>();
        <xsl:value-of select="@class"/>.set<xsl:value-of select="@property"/>(ftemp);
  </xsl:template>

  <xsl:template match="/config/option[@type='string-array']/introspect">
        astemp = <xsl:value-of select="@class"/>.get<xsl:value-of select="@property"/>();
        <xsl:value-of select="@class"/>.set<xsl:value-of select="@property"/>(astemp);
  </xsl:template>

  <xsl:template match="/config/option[@type='class']/introspect">
        ctemp = <xsl:value-of select="@class"/>.get<xsl:value-of select="@property"/>();
        <xsl:value-of select="@class"/>.set<xsl:value-of select="@property"/>(ctemp);
  </xsl:template>

  <xsl:template match="/config/option[@type='int-options']/introspect">
        itemp = <xsl:value-of select="@class"/>.get<xsl:value-of select="@property"/>();
        <xsl:value-of select="@class"/>.set<xsl:value-of select="@property"/>(itemp);
  </xsl:template>

  <xsl:template match="/config/option[@type='number']/introspect">
        itemp = <xsl:value-of select="@class"/>.get<xsl:value-of select="@property"/>();
        <xsl:value-of select="@class"/>.set<xsl:value-of select="@property"/>(itemp);
  </xsl:template>

</xsl:stylesheet>

