#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html
#
Pod::Spec.new do |s|
  s.name             = 'location_services'
  s.version          = '1.0.0'
  s.summary          = 'Determine if location services are enabled.'
  s.homepage         = 'https://github.com/ened/flutter_plugin_location_services'
  s.license          = { file: '../LICENSE' }
  s.author           = { 'Sebastian Roth' => 'seb@ivity.asia' }
  s.source           = { path: '.' }
  s.source_files = 'Classes/**/*'
  s.public_header_files = 'Classes/**/*.h'
  s.dependency 'Flutter'

  s.ios.deployment_target = '8.0'
end
