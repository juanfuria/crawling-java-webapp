<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Webcrawler demo</title>
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.1/semantic.min.css"/>
	<link rel="stylesheet" href="base.css"/>

	<script type="text/javascript" src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="spin.js"></script>
	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.1/semantic.min.js"></script>
	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.1/components/form.js"></script>
</head>
<body id="body">
<div class="ui segment">
	<h2 class="ui header">Webcrawler demo</h2>

	<form class="ui form" method="post" id="url_form" action="${pageContext.request.contextPath}/siteMap">
		<div class="ui error message">
			<ul class="list">
			</ul>
		</div>
		<div class="ui fluid action input">
			<input type="text" name="url" placeholder="http://example.com"/>
			<button type="submit" class="ui button blue">Crawl</button>
		</div>
	</form>
	<script>
		$('#url_form')
				.form({
					on: 'blur',
					fields: {
						url: {
							identifier: 'url',
							rules: [
								{
									type: 'url',
									prompt: ''
								}
							]
						}
					}
				});
		$('#url_form').submit(function () {

			var opts = {
				lines: 13 // The number of lines to draw
				, length: 28 // The length of each line
				, width: 14 // The line thickness
				, radius: 42 // The radius of the inner circle
				, scale: 1 // Scales overall size of the spinner
				, corners: 1 // Corner roundness (0..1)
				, color: '#000' // #rgb or #rrggbb or array of colors
				, opacity: 0.25 // Opacity of the lines
				, rotate: 0 // The rotation offset
				, direction: 1 // 1: clockwise, -1: counterclockwise
				, speed: 1 // Rounds per second
				, trail: 60 // Afterglow percentage
				, fps: 20 // Frames per second when using setTimeout() as a fallback for CSS
				, zIndex: 2e9 // The z-index (defaults to 2000000000)
				, className: 'spinner' // The CSS class to assign to the spinner
				, top: '50%' // Top position relative to parent
				, left: '50%' // Left position relative to parent
				, shadow: false // Whether to render a shadow
				, hwaccel: false // Whether to use hardware acceleration
				, position: 'absolute' // Element positioning
			}
			var target = document.getElementById('body')
			var spinner = new Spinner(opts).spin(target);
		});
	</script>
</div>
</body>
</html>
