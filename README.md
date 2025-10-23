Projeto: Sistema de Integração de APIs de Mídia Social (Adapter pattern)
================================================================================
Como usar
1. Requisitos: JDK 11+, Maven
2. Build: mvn package
3. Executar demo: mvn exec:java -Dexec.mainClass="com.example.social.Main"

O projeto demonstra:
- Padrão Adapter para várias plataformas (Twitter, Instagram, LinkedIn, TikTok)
- Interface unificada (SocialManager)
- Factory dinâmica por variável de ambiente / properties
- Estratégias de publicação (imediata / agendada)
- Thread-safe (registro e publicação concorrente)
- Uso de generics e tratamento de erro granular

Arquivos importantes: src/main/java/com/example/social/*
