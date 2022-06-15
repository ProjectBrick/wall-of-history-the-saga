.flash
	filename="wallofhistorythesaga.swf"
	version=6
	fps=30
	bbox=512x282
	background=#000000
	compress

	.font GillSans "fonts/gillsans.ttf"
	.font TradeMarkerLight "fonts/trademarker_light.ttf"

	.swf icon_2001 "icons/2001.swf"
	.swf icon_2002 "icons/2002.swf"
	.swf icon_2003 "icons/2003.swf"

	.box button_shape width=166 height=105 line=0 fill=#FFFFFF
	.box unglitch_shape width=1 height=54 line=0 fill=#000000

	.text button_2001_title font=GillSans size=130% color=#8CA82A text="Quest for the Masks"
	.text button_2002_title font=GillSans size=130% color=#8CA82A text="The Bohrok Swarms"
	.text button_2003_title font=GillSans size=130% color=#8CA82A text="The Bohrok-Kal Strike and\nthe Mask of Light"

	.text button_2001_text font=GillSans size=115% color=#FFFFFF text="Six legendary heroes, the Toa,\narrive on the island of Mata Nui.\nTo gain the strength they need\nto defeat the evil Makuta, they\nmust search for Kanohi Masks\nof Power."
	.text button_2002_text font=GillSans size=115% color=#FFFFFF text="Mighty swarms of insect-like\ncreatures, the Bohrok, sweep\nacross the island laying waste\nto everything in their path. To\nstop them, the Toa must travel\ndeep underground, where they\nwill be forever transformed."
	.text button_2003_text font=GillSans size=115% color=#FFFFFF text="Now changed into the more\npowerful Toa Nuva, the heroes\nface six elite Bohrok, the\nBohrok-Kal. The discovery of\nthe Mask of Light launches\nthem on a new adventure as\nthey challenge the might of the\nRahkshi. The arrival of a Toa of\nLight leads to a final\nconfrontation with Makuta."

	.sprite button_2001_idle
		.put icon_2001 pin=center
	.end

	.sprite button_2002_idle
		.put icon_2002 pin=center
	.end

	.sprite button_2003_idle
		.put icon_2003 pin=center
	.end

	.sprite button_2001_hover
		.put icon_2001 pin=center
	.end

	.sprite button_2002_hover
		.put icon_2002 pin=center
	.end

	.sprite button_2003_hover
		.put icon_2003 pin=center
	.end

	.button button_2001
		.show button_shape pin=center as=area
		.show button_2001_idle as=idle
		.show button_2001_hover as=hover,pressed
	.end

	.button button_2002
		.show button_shape pin=center as=area
		.show button_2002_idle as=idle
		.show button_2002_hover as=hover,pressed
	.end

	.button button_2003
		.show button_shape pin=center as=area
		.show button_2003_idle as=idle
		.show button_2003_hover as=hover,pressed
	.end

	.text menu_title font=TradeMarkerLight size=18pt color=#FFFFFF text="Wall of History: The Saga"

	.sprite menu
		.put menu_title pin=center x=256 y=12
		.put button_2001 x=87  y=75
		.put button_2002 x=256 y=75
		.put button_2003 x=425 y=75

		.put button_2001_title pin=top-left x=8   y=138 scale=16%
		.put button_2002_title pin=top-left x=177 y=138 scale=16%
		.put button_2003_title pin=top-left x=346 y=132 scale=16%

		.put button_2001_text  pin=top-left x=8   y=160 scale=16%
		.put button_2002_text  pin=top-left x=177 y=160 scale=16%
		.put button_2003_text  pin=top-left x=346 y=160 scale=16%
	.end

	.sprite episode
	.end

	.put episode

	.sprite unglitch
		.put unglitch_shape x=402 y=95
	.end

	.put unglitch

	.put menu

	.action:
		Array(function() {
			unglitch._visible = false;

			var extraFrames = 0;
			var paddingFrames = 0;
			var showEpisode = function(url, extra) {
				if (url == null) {
					extraFrames = 0;
					episode.unloadMovie();
					menu._visible = true;
				}
				else {
					extraFrames = extra;
					menu._visible = false;
					episode.loadMovie(url);
				}
			};

			menu.onEnterFrame = function() {
				// There may be a 1px line glitch on the side of the opening logo.
				// It only happens when not the root movie, it is very strange.
				// Easy to cover up with a black line, which is what this is doing.
				unglitch._visible = (
					episode._totalframes > 1 &&
					episode._currentframe < 225
				);

				if (episode._totalframes > 1) {
					// Return to menu if on replay screen for X frames.
					if (episode._currentframe == episode._totalframes) {
						if (paddingFrames-- < 1) {
							showEpisode(null);
						}
					}
					else {
						// Reset frame count if not on replay screen.
						paddingFrames = extraFrames;
					}
				}
			};

			menu.button_2001.onRelease = function() {
				showEpisode("story2001.swf", 30 * 3);
			};
			menu.button_2002.onRelease = function() {
				showEpisode("story2002.swf", 30 * 3);
			};
			menu.button_2003.onRelease = function() {
				// This one ends well before the sound does.
				showEpisode("story2003.swf", 30 * 30);
			};
		})[0]();
	.end
.end
