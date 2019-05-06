# sslocal slug

```
set "PATH=g:\Python27;g:\Python27\Scripts;%PATH%"
python setup.py develop
```

```
python
from sslug.command_line import main
main()
```

### generate *.tar.gz and *.whl
```
python setup.py sdist bdist_wheel
python setup.py register sdist bdist_wheel upload
python setup.py --help-commands
```
```
python -m pip install --user --upgrade setuptools wheel
python setup.py install
or
pip install .
pip uninstall sslug
```
### will reserve the name, upload package metadata, and create the pypi.python.org webpage
python setup.py register

### unit test
pip install nose
nosetests
python setup.py test
