
# CommentsBatch

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**addComment** | [**List&lt;NewComment&gt;**](NewComment.md) |  |  [optional]
**updateComment** | [**Map&lt;String, ClosableCommentPatch&gt;**](ClosableCommentPatch.md) |  |  [optional]
**updateStatus** | [**Map&lt;String, InnerEnum&gt;**](#Map&lt;String, InnerEnum&gt;) |  |  [optional]
**deleteComment** | **List&lt;String&gt;** |  |  [optional]
**addReply** | [**Map&lt;String, List&lt;NewReply&gt;&gt;**](List.md) |  |  [optional]
**updateReply** | [**Map&lt;String, Map&lt;String, CommentPatch&gt;&gt;**](Map.md) |  |  [optional]
**deleteReply** | [**Map&lt;String, List&lt;String&gt;&gt;**](List.md) |  |  [optional]


<a name="Map<String, InnerEnum>"></a>
## Enum: Map&lt;String, InnerEnum&gt;
Name | Value
---- | -----
OPEN | &quot;OPEN&quot;
RESOLVED | &quot;RESOLVED&quot;



