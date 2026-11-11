package com.example.myportfolio.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.myportfolio.dto.CommercialServiceDTO;
import com.example.myportfolio.dto.InquiryRequestDTO;
import com.example.myportfolio.dto.ServiceFeatureDTO;
import com.example.myportfolio.dto.ServiceQuoteRequest;
import com.example.myportfolio.dto.ServiceQuoteResponse;
import com.example.myportfolio.entity.ServiceInquiry;
import com.example.myportfolio.repository.ServiceInquiryRepository;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommercialServiceService {

    private final ServiceInquiryRepository inquiryRepository;
    private final EmailService emailService;
    private final Map<String, CommercialServiceDTO> catalog = new HashMap<>();

    public CommercialServiceService(ServiceInquiryRepository inquiryRepository, EmailService emailService) {
        this.inquiryRepository = inquiryRepository;
        this.emailService = emailService;
    }

    @PostConstruct
    public void initCatalog() {
        // 1. Web Applications
        registerService(CommercialServiceDTO.builder()
            .id("web-dev")
            .name("Web Applications & Enterprise Portals")
            .headline("Mission-critical full-stack web platforms engineered for speed, security, and scale.")
            .category("Engineering")
            .description("Custom SaaS platforms, customer portals, and internal enterprise tools built with React 19, Next.js, and Spring Boot microservices.")
            .basePrice(1500)
            .baseDeliveryDays(7)
            .badge("Core Engineering")
            .techStack(Arrays.asList("React 19", "Next.js", "TypeScript", "Tailwind CSS", "Spring Boot", "PostgreSQL", "Docker"))
            .deliverables(Arrays.asList(
                "Fully responsive Next.js / React production web app",
                "REST / GraphQL API integration and schema validation",
                "Automated CI/CD build scripts and Docker containerization",
                "Clean architectural documentation and post-launch SLA support"
            ))
            .features(Arrays.asList(
                ServiceFeatureDTO.builder().id("web-rbac").name("OAuth 2.0 & Role-Based Access Control").description("Google, Okta, JWT authentication with multi-tier permission matrix.").additionalPrice(450).additionalDays(2).isPopular(true).build(),
                ServiceFeatureDTO.builder().id("web-payment").name("Payment Gateway (Stripe / Razorpay)").description("Automated subscription checkout, webhooks, and invoice generation.").additionalPrice(500).additionalDays(2).isPopular(true).build(),
                ServiceFeatureDTO.builder().id("web-cms").name("Headless CMS & Dynamic Blog").description("Sanity / Strapi / Custom markdown publishing engine.").additionalPrice(350).additionalDays(1).isPopular(false).build(),
                ServiceFeatureDTO.builder().id("web-microservices").name("Distributed Microservices Architecture").description("Eureka dynamic service discovery, Feign RPC, and Redis cache.").additionalPrice(950).additionalDays(4).isPopular(false).build()
            ))
            .build());

        // 2. Mobile Applications
        registerService(CommercialServiceDTO.builder()
            .id("mobile-apps")
            .name("Mobile Apps & Server-Driven UI")
            .headline("Native performance iOS & Android applications with zero-release UI flexibility.")
            .category("Engineering")
            .description("Cross-platform React Native apps driven by dynamic JSON schemas (SDUI), offline storage, and push notifications.")
            .basePrice(2000)
            .baseDeliveryDays(10)
            .badge("iOS & Android Native")
            .techStack(Arrays.asList("React Native", "Expo", "iOS Swift", "Android Kotlin", "Server-Driven UI", "SQLite"))
            .deliverables(Arrays.asList(
                "Production iOS & Android application bundles (IPA / APK)",
                "Server-Driven UI component hydration system",
                "Deep-linking, push notification pipeline, and app store deployment readiness",
                "Offline-first local cache synchronization"
            ))
            .features(Arrays.asList(
                ServiceFeatureDTO.builder().id("mob-sdui").name("Server-Driven UI (SDUI) Remote Engine").description("Update screens, layouts, and forms remotely without App Store re-review.").additionalPrice(800).additionalDays(3).isPopular(true).build(),
                ServiceFeatureDTO.builder().id("mob-push").name("Push Notifications & WorkManager Sync").description("Background scheduled syncing, FCM tokens, and silent triggers.").additionalPrice(400).additionalDays(2).isPopular(false).build(),
                ServiceFeatureDTO.builder().id("mob-offline").name("Offline Cache & Conflict Resolution").description("Local SQLite/WatermelonDB sync with server retry queues.").additionalPrice(600).additionalDays(3).isPopular(true).build()
            ))
            .build());

        // 3. AI & Autonomous Agent Systems
        registerService(CommercialServiceDTO.builder()
            .id("ai-systems")
            .name("AI & Autonomous Agent Systems")
            .headline("Autonomous multi-agent swarms, RAG pipelines, and intelligent API automations.")
            .category("Data & Automation")
            .description("Enterprise AI systems powered by Model Context Protocol (MCP), tool calling, vector search, and strict security guards.")
            .basePrice(2500)
            .baseDeliveryDays(8)
            .badge("Next-Gen Intelligence")
            .techStack(Arrays.asList("Python", "FastAPI", "Spring AI", "LangChain", "OpenAI / Claude", "ChromaDB / Qdrant", "MCP"))
            .deliverables(Arrays.asList(
                "Autonomous agent swarm architecture with tool execution routing",
                "RAG document indexing pipeline with vector similarity search",
                "Streaming token SSE gateway (<15ms latency)",
                "Prompt injection defense and zero-direct-DB mutation protocol"
            ))
            .features(Arrays.asList(
                ServiceFeatureDTO.builder().id("ai-rag").name("Enterprise RAG Knowledge Base").description("Semantic search over PDF, docs, and internal knowledge sources.").additionalPrice(750).additionalDays(3).isPopular(true).build(),
                ServiceFeatureDTO.builder().id("ai-tools").name("Dynamic Tool & MCP System Dispatch").description("Allow agents to execute CRM, database, and terminal actions safely.").additionalPrice(900).additionalDays(3).isPopular(true).build(),
                ServiceFeatureDTO.builder().id("ai-guard").name("Security Guardrails & Hallucination Checks").description("Strict deterministic output validation and PII redaction.").additionalPrice(500).additionalDays(2).isPopular(false).build()
            ))
            .build());

        // 4. Desktop Applications
        registerService(CommercialServiceDTO.builder()
            .id("desktop-apps")
            .name("Cross-Platform Desktop Applications")
            .headline("Ultra-lightweight native desktop tools for macOS, Windows, and Linux.")
            .category("Engineering")
            .description("High-performance desktop software utilizing Tauri (Rust) or Electron with native system tray, SQLite, and IPC.")
            .basePrice(2200)
            .baseDeliveryDays(9)
            .badge("Desktop Systems")
            .techStack(Arrays.asList("Tauri", "Rust", "Electron", "React", "SQLite", "OS System Tray"))
            .deliverables(Arrays.asList(
                "Cross-platform signed binaries (.dmg, .exe, .AppImage)",
                "Native OS integration: system tray, notifications, file system",
                "High-speed IPC protocol with native hardware acceleration",
                "Automated background auto-update framework"
            ))
            .features(Arrays.asList(
                ServiceFeatureDTO.builder().id("desk-tray").name("Background System Tray Daemon").description("Run quietly in the menu bar with global hotkeys and instant popups.").additionalPrice(350).additionalDays(1).isPopular(true).build(),
                ServiceFeatureDTO.builder().id("desk-db").name("Local SQLite Encrypted Storage").description("Zero-cloud local encrypted storage with automated backups.").additionalPrice(450).additionalDays(2).isPopular(false).build()
            ))
            .build());

        // 5. Advanced Excel & BI Analytics Dashboards
        registerService(CommercialServiceDTO.builder()
            .id("excel-dashboards")
            .name("Excel & BI Analytics Dashboards")
            .headline("Institutional-grade financial models, automated KPI dashboards, and VBA macros.")
            .category("Data & Automation")
            .description("Custom executive reporting systems in Excel, Power BI, and Google Sheets with automated data refresh and macro tools.")
            .basePrice(800)
            .baseDeliveryDays(4)
            .badge("Data Analytics")
            .techStack(Arrays.asList("Microsoft Excel", "Power Query", "Power BI", "VBA Macros", "Google Apps Script", "SQL"))
            .deliverables(Arrays.asList(
                "Interactive multi-tab executive financial / operational workbook",
                "Automated ETL pipelines via Power Query pulling from SQL / CSV",
                "Dynamic interactive charts, slicers, and variance calculations",
                "Recorded and documented VBA macro automations"
            ))
            .features(Arrays.asList(
                ServiceFeatureDTO.builder().id("xl-vba").name("Custom VBA / Macro Automation Suite").description("One-click report consolidation, email dispatches, and data transforms.").additionalPrice(300).additionalDays(1).isPopular(true).build(),
                ServiceFeatureDTO.builder().id("xl-sql").name("Live Database SQL Connector").description("Direct ODBC / REST connector refreshing sheets from production DBs.").additionalPrice(400).additionalDays(2).isPopular(true).build()
            ))
            .build());

        // 6. Logo & Brand Identity Suite
        registerService(CommercialServiceDTO.builder()
            .id("logo-design")
            .name("Logo & Brand Identity Suite")
            .headline("Iconic visual brand marks, typography guidelines, and complete corporate identity systems.")
            .category("Design & Creative")
            .description("Distill your technology or business vision into a memorable vector mark with full brand guidelines.")
            .basePrice(600)
            .baseDeliveryDays(3)
            .badge("Brand Identity")
            .techStack(Arrays.asList("Figma", "Adobe Illustrator", "SVG Vectors", "Typography", "Color Science"))
            .deliverables(Arrays.asList(
                "Primary logo, secondary logo, and favicon in vector formats (SVG, AI, PDF)",
                "Full color palette specification (Hex, RGB, CMYK, Pantone)",
                "Typography scale and font pairing documentation",
                "Brand style guideline sheet and export assets"
            ))
            .features(Arrays.asList(
                ServiceFeatureDTO.builder().id("logo-3d").name("3D Metallic / Glass Logo Mockups").description("Hyper-realistic 4K 3D renders for landing pages and presentation decks.").additionalPrice(250).additionalDays(1).isPopular(true).build(),
                ServiceFeatureDTO.builder().id("logo-kit").name("Complete Social Media Kit").description("Profile avatars, banners, and headers for Twitter, LinkedIn, and YouTube.").additionalPrice(200).additionalDays(1).isPopular(false).build()
            ))
            .build());

        // 7. High-Conversion Marketing Banners & Ad Creatives
        registerService(CommercialServiceDTO.builder()
            .id("marketing-banners")
            .name("Marketing Banners & Ad Creatives")
            .headline("High-CTR promotional banners, social media ad packs, and presentation assets.")
            .category("Design & Creative")
            .description("Visually compelling marketing graphics optimized for conversions across Google Ads, LinkedIn, Twitter, and billboards.")
            .basePrice(500)
            .baseDeliveryDays(2)
            .badge("Marketing & Media")
            .techStack(Arrays.asList("Figma", "Photoshop", "Ad Specs", "Export Engines", "HTML5 Banners"))
            .deliverables(Arrays.asList(
                "Multi-size responsive banner packages (1200x628, 1080x1080, 1920x1080, 300x250)",
                "High-resolution PNG, WebP, and editable Figma source files",
                "A/B test variations with headline and CTA differentiators",
                "Fast 48-hour delivery guarantee"
            ))
            .features(Arrays.asList(
                ServiceFeatureDTO.builder().id("ban-animated").name("Animated HTML5 / GIF Banners").description("Smooth micro-animations to 3x click-through rates.").additionalPrice(250).additionalDays(1).isPopular(true).build(),
                ServiceFeatureDTO.builder().id("ban-pack").name("Omnichannel 15-Size Ad Expansion Pack").description("Complete dimension coverage for Meta, Google, LinkedIn, and App Store.").additionalPrice(350).additionalDays(1).isPopular(true).build()
            ))
            .build());

        // 8. Enterprise System & API Integrations
        registerService(CommercialServiceDTO.builder()
            .id("system-integrations")
            .name("System Integrations & Middleware")
            .headline("Seamless data bridges connecting CRMs, ERPs, payment rails, and cloud services.")
            .category("Engineering")
            .description("High-reliability middleware, webhook listeners, and ETL data sync between disparate enterprise platforms.")
            .basePrice(1800)
            .baseDeliveryDays(6)
            .badge("System Glue")
            .techStack(Arrays.asList("Spring Boot", "Kafka", "REST / Webhooks", "OAuth 2.0", "Redis", "Docker"))
            .deliverables(Arrays.asList(
                "Resilient integration service with automatic retries and dead-letter queues",
                "Bidirectional webhook receiver with HMAC signature verification",
                "Idempotent database reconciliation with zero data loss guarantee",
                "Prometheus and Grafana telemetry dashboards"
            ))
            .features(Arrays.asList(
                ServiceFeatureDTO.builder().id("int-dlq").name("Dead-Letter Queue & Auto-Retry Logic").description("Guarantee zero lost transactions during downstream network outages.").additionalPrice(400).additionalDays(2).isPopular(true).build(),
                ServiceFeatureDTO.builder().id("int-crm").name("Salesforce / HubSpot Enterprise Sync").description("Bi-directional contact, deal, and usage metrics pipeline.").additionalPrice(650).additionalDays(2).isPopular(false).build()
            ))
            .build());

        log.info("Initialized {} commercial services in catalog", catalog.size());
    }

    private void registerService(CommercialServiceDTO service) {
        catalog.put(service.getId(), service);
    }

    public List<CommercialServiceDTO> getAllServices() {
        return new ArrayList<>(catalog.values());
    }

    public Optional<CommercialServiceDTO> getServiceById(String id) {
        return Optional.ofNullable(catalog.get(id));
    }

    public ServiceQuoteResponse calculateQuote(ServiceQuoteRequest request) {
        CommercialServiceDTO service = catalog.get(request.getServiceId());
        if (service == null) {
            return null;
        }

        List<String> featureIds = request.getSelectedFeatureIds() != null ? request.getSelectedFeatureIds() : Collections.emptyList();
        int totalDays = service.getBaseDeliveryDays();
        int totalCost = service.getBasePrice();
        List<String> includedFeatures = new ArrayList<>();

        for (ServiceFeatureDTO feat : service.getFeatures()) {
            if (featureIds.contains(feat.getId())) {
                totalDays += feat.getAdditionalDays();
                totalCost += feat.getAdditionalPrice();
                includedFeatures.add(feat.getName());
            }
        }

        return ServiceQuoteResponse.builder()
            .serviceId(service.getId())
            .serviceName(service.getName())
            .basePrice(service.getBasePrice())
            .baseDeliveryDays(service.getBaseDeliveryDays())
            .totalCost(totalCost)
            .totalDeliveryDays(totalDays)
            .includedFeatures(includedFeatures)
            .deliverables(service.getDeliverables())
            .build();
    }

    public ServiceInquiry saveInquiry(InquiryRequestDTO request) {
        String featuresStr = request.getSelectedFeatures() != null ? String.join(", ", request.getSelectedFeatures()) : "";
        
        ServiceInquiry inquiry = ServiceInquiry.builder()
            .clientName(request.getClientName())
            .clientEmail(request.getClientEmail())
            .serviceId(request.getServiceId())
            .serviceName(request.getServiceName())
            .selectedFeatures(featuresStr)
            .estimatedDays(request.getEstimatedDays())
            .estimatedCost(request.getEstimatedCost())
            .targetDeadline(request.getTargetDeadline())
            .notes(request.getNotes())
            .status("New Lead")
            .build();

        ServiceInquiry saved = inquiryRepository.save(inquiry);
        log.info("Saved customer inquiry id={} for client={}", saved.getId(), saved.getClientName());

        // Send notification email asynchronously
        try {
            String subject = "New Project Inquiry from " + saved.getClientName() + " (" + saved.getServiceName() + ")";
            String body = "Client: " + saved.getClientName() + "\nEmail: " + saved.getClientEmail() + "\nService: " + saved.getServiceName()
                + "\nEstimated Budget: $" + saved.getEstimatedCost() + "\nEstimated Days: " + saved.getEstimatedDays()
                + "\nFeatures: " + saved.getSelectedFeatures();
            emailService.sendSimpleMail("sjha9563@gmail.com", subject, body);
        } catch (Exception e) {
            log.warn("Could not send email notification for inquiry: {}", e.getMessage());
        }

        return saved;
    }

    public List<ServiceInquiry> getAllInquiries() {
        return inquiryRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<ServiceInquiry> updateInquiryStatus(String id, String status, String notes) {
        return inquiryRepository.findById(id).map(inq -> {
            if (status != null && !status.isEmpty()) {
                inq.setStatus(status);
            }
            if (notes != null) {
                inq.setNotes(notes);
            }
            return inquiryRepository.save(inq);
        });
    }
}
