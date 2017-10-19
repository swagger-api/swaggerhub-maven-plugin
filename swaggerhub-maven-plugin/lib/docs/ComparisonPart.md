
# ComparisonPart

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**type** | [**TypeEnum**](#TypeEnum) | the type of change that this part describes * UNMODIFIED - this part is the same in both APIs. Only available for FULL compare. * INSERTED - this is present in &#x60;other&#x60; but missing in &#x60;base&#x60;. &#x60;base&#x60; will not be present. * DELETED - this is present in &#x60;base&#x60; but missing in &#x60;other&#x60;. &#x60;other&#x60; will not be present. * CHANGED - this has been changed between &#x60;base&#x60; and &#x60;other&#x60;. Both &#x60;base&#x60; and &#x60;other&#x60; present.  | 
**base** | [**ComparisonDetail**](ComparisonDetail.md) |  |  [optional]
**other** | [**ComparisonDetail**](ComparisonDetail.md) |  |  [optional]


<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
UNMODIFIED | &quot;UNMODIFIED&quot;
INSERTED | &quot;INSERTED&quot;
DELETED | &quot;DELETED&quot;
CHANGED | &quot;CHANGED&quot;



