var weatherIcon = document.getElementById("weather-icon");
	
	var val = document.getElementById("wc").value;
	  switch (val) {
      case 'Clouds':
		
          weatherIcon.src = "https://png.pngtree.com/png-clipart/20220810/ourmid/pngtree-cloud-and-sun-type-of-weather-png-image_6105935.png";
          break;
      case 'Clear':
          weatherIcon.src = "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/clear-day.svg";
          break;
      case 'Rain':
          weatherIcon.src = "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/partly-cloudy-day-rain.svg";
          break;
      case 'Mist':
          weatherIcon.src = "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/mist.svg";
          break;
      case 'Snow':
          weatherIcon.src = "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/overcast-day-snow.svg";
          break;
      case 'Haze':
          weatherIcon.src = "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/partly-cloudy-day-haze.svg";
          break;
      case 'Smoke':
          weatherIcon.src = "data:image/jpeg;base64,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";
  }
