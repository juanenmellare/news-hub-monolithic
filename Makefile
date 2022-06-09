run-tests:
	grails test-app

run-tests-coverage:
	./gradlew cloverGenerateReport
	open build/reports/clover/html/index.html
