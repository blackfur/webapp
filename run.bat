$ gunicorn <Project-Name>.wsgi:application --log-level=debug --bind 0.0.0.0:8000
gunicorn website.wsgi:application --log-level=debug --bind 0.0.0.0:8000
