TaskName=Juice faggot
GRADLE_HOME=g:\gradle-5.4.1

GRADLE=$(GRADLE_HOME)\bin\gradle.bat
# -q --quiet
# -x exclude
# -continue continue even failure Occurs
# -b specify build file
# -q tasks --all
# -q help -task
# -q dependencies
# -q dependencyInsight
# -q buildEnvironment
# -q properties
run:
	$(GRADLE) -q $(TaskName)
	$(GRADLE) $(TaskName) -x tv
	$(GRADLE) -b foo/foo.gradle meal
	$(list hierarchy)
	$(GRADLE) -q projects tasks
