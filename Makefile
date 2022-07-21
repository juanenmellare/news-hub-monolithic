run-tests:
	grails test-app

run-tests-coverage:
	grails clean
	./gradlew cloverGenerateReport
	open build/reports/clover/html/index.html
