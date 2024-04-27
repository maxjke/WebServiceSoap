<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" doctype-system="about:legacy-compat" encoding="UTF-8"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Client Information</title>
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
                <link rel="stylesheet" href="styles.css"/>
            </head>
            <body>
                <div class="container">
                    <img src="images/Untitled.png" class="img-fluid mb-3" alt="Client Profile Header"/>
                    <h1 class="custom-header">Client Profile</h1>
                   
                    <div class="card mb-3">
                        <div class="card-body">
                            <h4 class="card-title">Client Details</h4>
                            <p class="card-text">Name: <xsl:value-of select="client/firstName"/></p>
							<p class="card-text">Surname: <xsl:value-of select="client/lastName"/></p>
                            <p class="card-text">Username: <xsl:value-of select="client/account/username"/></p>
                            <p class="card-text">Password: <xsl:value-of select="client/account/password"/></p>
                        </div>
                    </div>
                    <h2><img src="images/bankaccount.png" alt="Accounts Icon"/> Bank Accounts</h2>
                    <xsl:apply-templates select="client/BankAccountList/BankAccount"/>
                    <h2><img src="images/loan.png" alt="Loans Icon"/> Loans</h2>
                    <xsl:apply-templates select="client/LoanList/Loan"/>
                    <h2><img src="images/creditcard1.png" alt="Credit Cards Icon"/> Credit Cards</h2>
                    <xsl:apply-templates select="client/CreditCardList/CreditCard"/>
                </div>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="BankAccount">
    <div class="card mb-3">
        <div class="card-body">
            <h5 class="card-title">Account Details</h5>
            <p class="card-text">Balance: <xsl:value-of select="balance"/> <xsl:value-of select="currency"/></p>
            <p class="card-text">
                Status: 
                <xsl:choose>
                    <xsl:when test="status='active'">
                        <img src="images/green-check.png" alt="Active" class="small-icon"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <img src="images/red-cross.png" alt="Inactive" class="small-icon"/>
                    </xsl:otherwise>
                </xsl:choose>
            </p>
        </div>
    </div>
</xsl:template>


   
    <xsl:template match="Loan">
        <div class="card mb-3">
            <div class="card-body">
                <h5 class="card-title">Loan Information</h5>
                <p class="card-text">Amount: <xsl:value-of select="loanAmount"/></p>
                <p class="card-text">Monthly Payment: <xsl:value-of select="monthlyPayment"/></p>
                <p class="card-text">
				Status: 
                <xsl:choose>
                    <xsl:when test="status='paid'">
                        <img src="images/green-check.png" alt="Paid" class="small-icon"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <img src="images/red-cross.png" alt="Unpaid" class="small-icon"/>
                    </xsl:otherwise>
                </xsl:choose>
				</p>
            </div>
        </div>
    </xsl:template>

    
    <xsl:template match="CreditCard">
        <div class="card mb-3">
            <div class="card-body">
                <h5 class="card-title">Credit Card Details</h5>
                <p class="card-text">Number: <xsl:value-of select="cardNumber"/></p>
                <p class="card-text">Limit: <xsl:value-of select="cardLimit"/></p>
            </div>
        </div>
    </xsl:template>

</xsl:stylesheet>
