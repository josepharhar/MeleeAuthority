var canvases = document.getElementsByClassName('angle-canvas');
for (var i = 0; i < canvases.length; i++) {
  var canvas = canvases[i];
  var angle = canvas.getAttribute('data-angle');
  console.log('angle #' + i + ': ' + angle);
  var context = canvas.getContext('2d');

  var width = 32;
  var radius = width / 2;
  context.lineWidth = 2;
  context.strokeStyle = 'white';

  context.beginPath();
  //context.arc(100, 75, 50, 0, 2 * Math.PI);
  context.arc(radius, radius, radius - 2, 0, 2 * Math.PI);
  context.stroke();

  // TODO add tooltips or other text with angles

  context.lineWidth = 3;
  if (angle >= 260 && angle <= 280) {
    context.strokeStyle = 'yellow';
  } else {
    context.strokeStyle = 'red';
  }
  if (angle == 361) {
    var gradient = context.createRadialGradient(radius, radius, 0, radius, radius, radius * 0.7);
    gradient.addColorStop(0, 'red');
    gradient.addColorStop(1, 'transparent');
    context.fillStyle = gradient;
    context.fillRect(0, 0, width, width);
    angle = 45;
  }
  context.beginPath();
  context.moveTo(width / 2, width / 2);
    angle = angle * Math.PI / 180;
    context.lineTo(
        radius + radius * 0.8 * Math.cos(angle),
        radius - radius * 0.8 * Math.sin(angle));
  context.stroke();
}
