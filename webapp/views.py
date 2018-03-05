from django.shortcuts import render
# from django.http import HttpResponse


# Create your views here.
def index(req):
    # return HttpResponse("<h2>Hello World!</h2>")
    return render(req, 'webapp/home.html')


def contact(req):
    return render(req, 'webapp/basic.html',
                  {'content': ['Please contact me:',
                               'junehwang@gmail.com']})
