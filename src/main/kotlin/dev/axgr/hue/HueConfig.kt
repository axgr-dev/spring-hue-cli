package dev.axgr.hue

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@ConfigurationProperties("hue")
data class HueProperties(val bridge: String, val username: String)

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(HueProperties::class)
class HueConfig {

  @Bean
  fun client(builder: RestClient.Builder, props: HueProperties): RestClient {
    return builder
      .baseUrl("http://${props.bridge}/api/${props.username}")
      .build()
  }
}
