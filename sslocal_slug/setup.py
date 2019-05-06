import setuptools

with open("README.md", "r") as fh:
    long_description = fh.read()

setuptools.setup(
    name="sslug",
    version="0.0.1",
    author="iwillb5",
    author_email="junehwang0@gmail.com",
    description="adapt sslocal to windows platform",
    long_description=long_description,
    long_description_content_type="text/markdown",
    url="https://github.com/pypa/sampleproject",
    packages=setuptools.find_packages(),
    classifiers=[
        'Development Status :: 3 - Alpha',
        "Programming Language :: Python :: 3",
        "License :: OSI Approved :: MIT License",
        "Operating System :: OS Independent",
        'Topic :: Text Processing :: Linguistic',
    ],
    keywords='shadowsocks',
    license='MIT',
    # packages=[''],
    include_package_date=True,
    scripts=['bin/sslug.bat','bin/sslug.sh'],
    install_requires=[
        'markdown'
        ],
    # dependency_links=['http://github.com/user/repo/tarball/master#egg=package-1.0'],
    zip_safe=False,
    test_suite='nose.collector',
    tests_require=['nose']
)
