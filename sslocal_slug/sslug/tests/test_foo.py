from unittest import TestCase

from sslug.foo import joke

class TestJoke(TestCase):
    def test_is_string(self):
        s = joke()
        self.assertTrue(isinstance(s, basestring))
