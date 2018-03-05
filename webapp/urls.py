from django.urls import path
from django.conf.urls import url
# , include
from . import views
# , models
from webapp.models import Post
from django.views.generic import ListView, DetailView
from django.conf import settings
from django.conf.urls.static import static


urlpatterns = [
    path('', views.index, name='index'),
    url(r'^contact$', views.contact, name='contact'),
    url(r'^blog/?$',
        ListView.as_view(
            queryset=Post.objects.all().order_by("-date")[:32],
            template_name='webapp/blog.html')),
    url(r'^blog/(?P<pk>\d+)$',
        DetailView.as_view(model=Post,
                           template_name='webapp/post.html'))
] + static(settings.STATIC_URL, document_root=settings.STATIC_ROOT)
