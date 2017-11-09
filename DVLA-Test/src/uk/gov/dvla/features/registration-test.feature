Feature: DVLA data verification

	Scenario Outline: Verify car registration details
		Given user enters car registration number <registration>
		When he clicks Continue button 
		Then he should see car make <make> and model <color>
		
	Examples:
|registration|make|color|
|FG54WDV|TOYOTA|SILVER|
