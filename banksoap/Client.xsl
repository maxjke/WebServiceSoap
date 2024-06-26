<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">


    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" />


    <xsl:template match="/client">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="simple"
                                       page-height="29.7cm" page-width="21cm" margin="2cm">
                    <fo:region-body margin="1cm" />
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="simple">
                <fo:flow flow-name="xsl-region-body">

                    <fo:block text-align="center">
                        <fo:external-graphic src="clientinfo.svg" content-width="scale-to-fit" height="5cm"/>
                    </fo:block>
                    <fo:block font-size="14pt" font-family="Arial, sans-serif" space-after="5mm">
                        <fo:block font-weight="bold" text-align="center" color="blue">Client Information</fo:block>
                        <fo:block>First Name: <xsl:value-of select="firstName"/></fo:block>
                        <fo:block>Last Name: <xsl:value-of select="lastName"/></fo:block>
                        <fo:block>Username: <xsl:value-of select="account/username"/></fo:block>
                        <fo:block>Password: <xsl:value-of select="account/password"/></fo:block>
                    </fo:block>


                    <fo:block font-weight="bold" margin-top="10pt" font-size="12pt">
                        <fo:block font-weight="bold" text-align="center" color="green">Bank Accounts</fo:block>
                        <fo:block>
                            <fo:external-graphic src="bankaccount.svg" content-width="scale-to-fit" height="3cm"/>
                        </fo:block>
                        <xsl:for-each select="BankAccountList/BankAccount">

                            <fo:table width="100%" table-layout="fixed" border="solid 1px black" border-collapse="separate" border-spacing="2px">
                                <fo:table-column column-width="proportional-column-width(4)"/>
                                <fo:table-column column-width="proportional-column-width(1)"/>
                                <fo:table-body start-indent="5mm">
                                    <fo:table-row>
                                        <fo:table-cell border="solid 1px black" padding="2mm">
                                            <fo:block>Balance: <xsl:value-of select="balance"/></fo:block>
                                            <fo:block>Currency: <xsl:value-of select="currency"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border="solid 1px black" text-align="center" display-align="center">

                                            <fo:block>
                                                <fo:external-graphic>
                                                    <xsl:attribute name="src">
                                                        <xsl:choose>
                                                            <xsl:when test="status='active'">green-check.svg</xsl:when>
                                                            <xsl:otherwise>red-cross.svg</xsl:otherwise>
                                                        </xsl:choose>
                                                    </xsl:attribute>
                                                    <xsl:attribute name="content-width">scale-down-to-fit</xsl:attribute>
                                                    <xsl:attribute name="height">1cm</xsl:attribute>
                                                </fo:external-graphic>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </xsl:for-each>
                    </fo:block>


                    <fo:block font-weight="bold" margin-top="10pt" font-size="12pt">
                        <fo:block font-weight="bold" text-align="center" color="red">Loans</fo:block>
                        <fo:block>
                            <fo:external-graphic src="loan.svg" content-width="scale-to-fit" height="3cm"/>
                        </fo:block>
                        <xsl:for-each select="LoanList/Loan">
                            <fo:table width="100%" table-layout="fixed" border="solid 1px black" border-collapse="separate" border-spacing="2px">
                                <fo:table-column column-width="proportional-column-width(4)"/>
                                <fo:table-column column-width="proportional-column-width(1)"/>
                                <fo:table-body start-indent="5mm">
                                    <fo:table-row>
                                        <fo:table-cell border="solid 1px black" padding="2mm">
                                            <fo:block>Loan Amount: <xsl:value-of select="loanAmount"/></fo:block>
                                            <fo:block>Start Date: <xsl:value-of select="loanStartDate"/></fo:block>
                                            <fo:block>End Date: <xsl:value-of select="loanEndDate"/></fo:block>
                                            <fo:block>Monthly Payment: <xsl:value-of select="monthlyPayment"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border="solid 1px black" text-align="center" display-align="center">
                                            <fo:block>
                                                <fo:external-graphic>
                                                    <xsl:attribute name="src">
                                                        <xsl:choose>
                                                            <xsl:when test="status='paid'">green-check.svg</xsl:when>
                                                            <xsl:otherwise>red-cross.svg</xsl:otherwise>
                                                        </xsl:choose>
                                                    </xsl:attribute>
                                                    <xsl:attribute name="content-width">scale-down-to-fit</xsl:attribute>
                                                    <xsl:attribute name="height">1cm</xsl:attribute>
                                                </fo:external-graphic>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </xsl:for-each>
                    </fo:block>



                    <fo:block font-weight="bold" margin-top="10pt" font-size="12pt">
                        <fo:block font-weight="bold" text-align="center" color="purple">Credit Cards</fo:block>
                        <fo:block>
                            <fo:external-graphic src="creditcard.svg" content-width="scale-to-fit" height="3cm"/>
                        </fo:block>
                        <xsl:for-each select="CreditCardList/CreditCard">

                            <fo:table width="100%" table-layout="fixed" border="solid 1px black" border-collapse="separate" border-spacing="2px">
                                <fo:table-column column-width="proportional-column-width(5)"/>
                                <fo:table-body start-indent="5mm">
                                    <fo:table-row>
                                        <fo:table-cell border="solid 1px black" padding="2mm">
                                            <fo:block>Card Number: <xsl:value-of select="cardNumber"/></fo:block>
                                            <fo:block>Limit: <xsl:value-of select="cardLimit"/></fo:block>
                                            <fo:block>Expiry: <xsl:value-of select="expireDate"/></fo:block>
                                        </fo:table-cell>


                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </xsl:for-each>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
