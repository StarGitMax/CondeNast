package com.CondeNast.Core;

import org.sikuli.script.Pattern;

public class ImagePatterns extends WebDriverClass
{
	public static Pattern address;
	public static Pattern CreateQuotes;
	public static Pattern FivePerPage;
	public static Pattern TenPerPage;
	public static Pattern FifteenPerPage;
	public static Pattern TwentyPerPage;
	public static Pattern CopyQuote;
	public static Pattern Quotes;
	public static Pattern IdentifyingOpportunity;
	public static Pattern ShapingProposal;
	public static Pattern VerbalCommitment;
	public static Pattern Contracting;
	public static Pattern ClosedWon;
	public static Pattern ClosedLost;
	public static Pattern SetUp;
	public static Pattern SearchOpportunities;
	public static Pattern SearchMarketAccounts;
	public static Pattern SearchAccounts;
	public static Pattern SearchContacts;
	public static Pattern SearchClientBrand;
	public static Pattern SearchClientProduct;
	public static Pattern RelatedTab;
	public static Pattern AccountClient;
	public static Pattern OpportunityDetailsTab;
	public static Pattern PipelineDelivery;
	public static Pattern MarketAccountEdit;
	public static Pattern MarketAccountDetailsTab;
	
	static
	{
		address = new Pattern(sPath+"/ImageRepository/EnterAddress.PNG");
		CreateQuotes = new Pattern(sPath+"/ImageRepository/CreateQuotes.PNG");
		FivePerPage =  new Pattern(sPath+"/ImageRepository/FivePerPage.PNG");
		TenPerPage = new Pattern(sPath+"/ImageRepository/TenPerPage.PNG");
		FifteenPerPage =  new Pattern(sPath+"/ImageRepository/FifteenPerPage.PNG");
		TwentyPerPage = new Pattern(sPath+"/ImageRepository/TwentyPerPage.PNG");
		CopyQuote = new Pattern(sPath+"/ImageRepository/CopyQuote.PNG");
		Quotes = new Pattern(sPath+"/ImageRepository/Quotes.PNG");
		IdentifyingOpportunity = new Pattern(sPath+"/ImageRepository/IdentifyingOpportunity.PNG");
		ShapingProposal = new Pattern(sPath+"/ImageRepository/ShapingProposal.PNG");
		VerbalCommitment = new Pattern(sPath+"/ImageRepository/VerbalCommitment.PNG");
		Contracting = new Pattern(sPath+"/ImageRepository/Contracting.PNG");
		ClosedWon = new Pattern(sPath+"/ImageRepository/ClosedWon.PNG");
		ClosedLost = new Pattern(sPath+"/ImageRepository/ClosedLost.PNG");
		SetUp = new Pattern(sPath+"/ImageRepository/SetUp.PNG");
		SearchOpportunities = new Pattern(sPath+"/ImageRepository/SearchOpportunities.PNG");
		SearchMarketAccounts = new Pattern(sPath+"/ImageRepository/SearchMarketAccounts.PNG");
		SearchAccounts = new Pattern(sPath+"/ImageRepository/SearchAccounts.PNG");
		SearchContacts = new Pattern(sPath+"/ImageRepository/SearchContacts.PNG");
		SearchClientBrand = new Pattern(sPath+"/ImageRepository/SearchClientBrand.PNG");
		SearchClientProduct = new Pattern(sPath+"/ImageRepository/SearchClientProduct.PNG");
		RelatedTab = new Pattern(sPath+"/ImageRepository/RelatedTab.PNG");
	    AccountClient = new Pattern(sPath+"/ImageRepository/AccountClient.PNG");
	    OpportunityDetailsTab =  new Pattern(sPath+"/ImageRepository/OpportunityDetailsTab.PNG");
	    PipelineDelivery =  new Pattern(sPath+"/ImageRepository/PipelineDelivery.PNG");
	    MarketAccountEdit = new Pattern(sPath+"/ImageRepository/MarketAccountEdit.PNG");
	    MarketAccountDetailsTab = new Pattern(sPath+"/ImageRepository/MarketAccountDetailsTab.PNG");
	}
}
